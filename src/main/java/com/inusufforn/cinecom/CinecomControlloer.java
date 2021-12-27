/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inusufforn.cinecom.common.constants.RatingValue;
import com.inusufforn.cinecom.common.util.Rating;
import com.inusufforn.cinecom.dto.LoginDto;
import com.inusufforn.cinecom.dto.ReviewDto;
import com.inusufforn.cinecom.dto.ReviewSearchDto;
import com.inusufforn.cinecom.dto.ReviewUserRegisterDto;
import com.inusufforn.cinecom.entity.MonthlyListReviewItem;
import com.inusufforn.cinecom.entity.Review;
import com.inusufforn.cinecom.entity.ReviewUser;
import com.inusufforn.cinecom.service.ReviewService;
import com.inusufforn.cinecom.service.ReviewUserService;

/**
 * コントローラ.
 *
 * @author isaku
 */
@Controller
public class CinecomControlloer {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewUserService reviewUserService;

    /** 評価の値と表示文字列の対応テーブル */
    static final Rating [] ratings = {
        new Rating(1, RatingValue._1),
        new Rating(2, RatingValue._2),
        new Rating(3, RatingValue._3),
        new Rating(4, RatingValue._4),
        new Rating(5, RatingValue._5),
    };

    /**
     * 直近のレビュー情報を検索し、初期画面を表示する.
     * 
     * @param authentication
     * @param pageable
     * @param model
     * @return 初期画面へのパス
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String model(Authentication authentication, Pageable pageable, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        boolean isEableOnly = !hasAdminRole(authentication);

        Page<MonthlyListReviewItem> page= reviewService.getListByLastYearMonth(pageable, isEableOnly);
        
        List<MonthlyListReviewItem> monthlyReviewItemList = page.getContent();
        // Page情報を設定
        model.addAttribute("page", page);

        // 遷移先を設定
        if (monthlyReviewItemList.size() != 0) {
            DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyyMM");
            String yearMonth = monthlyReviewItemList.get(0).getWatchedDate().format(datetimeformatter);
            model.addAttribute("url", "/yearmonthlist/" + yearMonth);
        }

        // 表示情報を設定
        model.addAttribute("monthlyList", page.getContent());

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(isEableOnly);
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "index";
    }

    /**
     * レビュー詳細画面に遷移する.
     * 
     * @param id
     * @param model
     * @return レビュー詳細画面へのパス
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(Authentication authentication, @PathVariable Integer id, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Review review = reviewService.getDetail(id);
        model.addAttribute("detail", review);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        return "/detail";
    }

    /**
     * レビューの新規登録画面に遷移する.
     * 
     * @param authentication
     * @param model
     * @return 新規登録画面へのパス
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Authentication authentication, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        // 評価のリストを設定
        model.addAttribute("ratingList", Arrays.asList(ratings));
        // 評価の初期値を設定
        model.addAttribute("selectedValue", 1);

        // デフォルトで「表示する」チェックボックスがチェックされているように設定
        ReviewDto review = new ReviewDto();
        review.setEnable(Short.valueOf("1"));

        model.addAttribute("reviewDto", review);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        return "/add";
    }

    /**
     * レビューの新規登録を行う.
     * 
     * @param authentication
     * @param pageable
     * @param reviewDto
     * @param result
     * @param attributes
     * @param model
     * @param principal
     * @return バリデーションチェックのエラーがあったとき、新規登録画面へのパス。それ以外、初期画面へのパス
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String creatte(Authentication authentication, Pageable pageable, @Validated @ModelAttribute ReviewDto reviewDto, BindingResult result, RedirectAttributes attributes, Model model, Principal principal) {

        // 検索条件の入力エリアを生成(必ずメソッドの最初に行う)
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        boolean isEableOnly = !hasAdminRole(authentication);

        // アーカイブのリンクリストを生成(途中でメソッドを抜ける可能性があれば、その前に行う)
        List<String> yearMonthList = reviewService.getYearMonthList(isEableOnly);
        model.addAttribute("yearMonthList", yearMonthList);

        // バリデーションチェックのエラーがあるかどうかをチェック
        if(result.hasErrors()){
            // 評価のリストを設定
            model.addAttribute("ratingList", Arrays.asList(ratings));
            // 評価の初期値を設定
            model.addAttribute("selectedValue", reviewDto.getRating());

            model.addAttribute("reviewDto", reviewDto);

            return "/add";
        }

        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setShortComment(reviewDto.getShortComment());
        review.setWatchedDate(reviewDto.getWatchedDate());
        review.setWatchedTime(reviewDto.getWatchedTime());
        review.setTheater(reviewDto.getTheater());
        review.setScreen(reviewDto.getScreen());
        review.setMainComment(reviewDto.getMainComment());
        review.setEnable(reviewDto.getEnable());
        review.setRating(reviewDto.getRating());
        // ログイン情報から作成者を設定
        review.setCreatedBy(principal.getName());
        // ログイン情報から更新者を設定
        review.setUpdatedBy(principal.getName());

        reviewService.insert(review);

        Page<MonthlyListReviewItem> page= reviewService.getListByLastYearMonth(pageable, isEableOnly);
        // Page情報を設定
        model.addAttribute("page", page);
        // 遷移先を設定
        model.addAttribute("url", "/");
        // 表示情報を設定
        model.addAttribute("monthlyList", page.getContent());

        return "index";
    }

    /**
     * 年月指定で検索処理を行う.
     * 
     * @param authentication
     * @param pageable
     * @param yearMonth
     * @param model
     * @return 初期画面へのパス
     */
    @RequestMapping(value = "/yearmonthlist/{yearMonth}", method = RequestMethod.GET)
    public String yearMonthList(Authentication authentication, Pageable pageable, @PathVariable String yearMonth, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Page<MonthlyListReviewItem> page= reviewService.getListByYearMonth(pageable, yearMonth, !hasAdminRole(authentication));
        // Page情報を設定
        model.addAttribute("page", page);
        // 遷移先を設定
        model.addAttribute("url", "/yearmonthlist/" + yearMonth);
        // 表示情報を設定
        model.addAttribute("monthlyList", page.getContent());

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "index";
    }

    /**
     * レビュー編集画面に遷移する.
     * 
     * @param id
     * @param model
     * @return レビュー編集画面へのパス
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Authentication authentication, @PathVariable Integer id, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Review review = reviewService.getDetail(id);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setShortComment(review.getShortComment());
        reviewDto.setWatchedDate(review.getWatchedDate());
        reviewDto.setWatchedTime(review.getWatchedTime());
        reviewDto.setTheater(review.getTheater());
        reviewDto.setScreen(review.getScreen());
        reviewDto.setMainComment(review.getMainComment());
        reviewDto.setEnable(review.getEnable());
        reviewDto.setRating(review.getRating());

        model.addAttribute("reviewDto", reviewDto);

        // 評価のリストを設定
        model.addAttribute("ratingList", Arrays.asList(ratings));
        // 評価の初期値を設定
        model.addAttribute("selectedValue", reviewDto.getRating());

       // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "/edit";
    }

    /**
     * レビューの更新を行い、更新後の詳細情報を表示する.
     * 
     * @param reviewDto
     * @param result
     * @param model
     * @param httpServletRequest
     * @param principal
     * @return バリデーションチェックのエラーがあったとき、レビュー更新画面へのパス。それ以外、レビュー編集画面へのパス
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Authentication authentication, @Validated @ModelAttribute ReviewDto reviewDto, BindingResult result, Model model, HttpServletRequest httpServletRequest, Principal principal) {

        // 検索条件の入力エリアを生成(必ずメソッドの最初に行う)
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        // アーカイブのリンクリストを生成(途中でメソッドを抜ける可能性があれば、その前に行う)
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        // バリデーションチェックのエラーがあるかどうかをチェック
        if(result.hasErrors()){
            // 評価のリストを設定
            model.addAttribute("ratingList", Arrays.asList(ratings));
            // 評価の初期値を設定
            model.addAttribute("selectedValue", reviewDto.getRating());

            model.addAttribute("reviewDto", reviewDto);

            return "/edit";
        }

        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setShortComment(reviewDto.getShortComment());
        review.setWatchedDate(reviewDto.getWatchedDate());
        review.setWatchedTime(reviewDto.getWatchedTime());
        review.setTheater(reviewDto.getTheater());
        review.setScreen(reviewDto.getScreen());
        review.setMainComment(reviewDto.getMainComment());
        // 「表示する」チェックボックスがチェックされていなければ、値は送信されない
        review.setEnable((reviewDto.getEnable() != null)?
            reviewDto.getEnable() : Short.valueOf("0"));
        review.setRating(reviewDto.getRating());
        // ログイン情報から更新者を設定
        review.setUpdatedBy(principal.getName());
        // 更新日時を設定
        review.setUpdated(LocalDateTime.now());

        reviewService.update(review);

        review = reviewService.getDetail(reviewDto.getId());
        model.addAttribute("detail", review);

        return "/detail";
    }

    /**
     * ユーザ登録メニューの選択でユーザ登録画面に遷移する。
     * 
     * @param model
     * @return ユーザ登録画面へのパス
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Authentication authentication, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        ReviewUserRegisterDto reviewUserRegisterDto =new ReviewUserRegisterDto();
        Map<String, String> authorities = new LinkedHashMap<String, String>();

        authorities.put("ROLE_ADMIN", "管理者ユーザ");
        authorities.put("ROLE_USER", "一般ユーザ");        
        reviewUserRegisterDto.setAuthorities(authorities);

        model.addAttribute("reviewUserRegisterDto", reviewUserRegisterDto);
        model.addAttribute("defaultAuth", "ROLE_USER");
        
        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        return "/user";
    }

    /**
     * ユーザを登録する.
     * 
     * @param user
     * @param model
     * @return バリデーションチェックのエラーがあったとき、ユーザ登録画面へのパス。それ以外、ユーザ登録完了画面へのパス
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String register(Authentication authentication, @Validated @ModelAttribute ReviewUserRegisterDto reviewUserRegisterDto, BindingResult result, Model model) {

        // 検索条件の入力エリアを生成(必ずメソッドの最初に行う)
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        // アーカイブのリンクリストを生成(途中でメソッドを抜ける可能性があれば、その前に行う)
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        // バリデーションチェックのエラーがあるかどうかをチェック
        if(result.hasErrors()){
            Map<String, String> authorities = new LinkedHashMap<String, String>();

            authorities.put("ROLE_ADMIN", "管理者ユーザ");
            authorities.put("ROLE_USER", "一般ユーザ");        
            reviewUserRegisterDto.setAuthorities(authorities);

            model.addAttribute("reviewUserRegisterDto", reviewUserRegisterDto);
            model.addAttribute("defaultAuth", "ROLE_USER");

            return "/user";
        }

       PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(reviewUserRegisterDto.getPassword());

        ReviewUser reviewUser = new ReviewUser();
        
        reviewUser.setUsername(reviewUserRegisterDto.getUsername());
        reviewUser.setPassword(hashedPassword);
        reviewUser.setAuthority(reviewUserRegisterDto.getAuthority());

        reviewUserService.insert(reviewUser);

        model.addAttribute("reviewUserRegisterDto", reviewUserRegisterDto);

        return "/registered";
    }

    /**
     * ログイン画面への遷移を行う.
     * 
     * @param model
     * @return ログイン画面へのパス
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Authentication authentication, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        LoginDto login =new LoginDto();
        model.addAttribute("login", login);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        return "/login";
    }

    /**
     * ログアウト画面への遷移を行う.
     * 
     * @param model
     * @return ログアウト画面へのパス
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Authentication authentication, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(!hasAdminRole(authentication));
        model.addAttribute("yearMonthList", yearMonthList);

        return "/logout";
    }

    /**
     * レビューの検索処理を行う.
     * 
     * @param pageable
     * @param search
     * @param model
     * @return レビュー検索結果表示画面へのパス
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String result(Authentication authentication, Pageable pageable, @ModelAttribute ReviewSearchDto search, Model model) {

        // 検索条件の入力エリアを引継ぎ
        model.addAttribute("search", search);
 
        boolean isEableOnly = !hasAdminRole(authentication);

        Page<MonthlyListReviewItem> page= reviewService.getSearchList(pageable, search.getTitle(), isEableOnly);
        // Page情報を設定
        model.addAttribute("page", page);
        // 遷移先を設定
        model.addAttribute("url", "/search");
        // 表示情報を設定
        model.addAttribute("searchList", page.getContent());
        // 検索条件を設定
        model.addAttribute("searchTitle", search.getTitle());

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList(isEableOnly);
        model.addAttribute("yearMonthList", yearMonthList);

        return "/search";
    }

    /**
     * 認証済みのユーザ情報が管理者権限が含まれているかどうかをチェックする.
     * 
     * @param authentication 認証済みのユーザ情報
     * @return 管理者権限(ROLE_ADMIN)が含まれていればtrue
     */
    private boolean hasAdminRole(Authentication authentication) {
        if (authentication != null) {
            Set<String> authoritiesSet = authentication
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            return !authoritiesSet.contains("ROLE_ADMIN");
        } else {
            return false;
        }
    }

}
