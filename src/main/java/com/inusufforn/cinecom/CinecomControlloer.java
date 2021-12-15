/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * @author isaku
 *
 */
@Controller
public class CinecomControlloer {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewUserService reviewUserService;

    /**
     * 直近のレビュー情報を検索し、初期画面を表示する.
     * 
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String model(Pageable pageable, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Page<MonthlyListReviewItem> page= reviewService.getPage(pageable);
        
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
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "index";
    }

    /**
     * レビュー詳細に遷移する.
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer id, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Review review = reviewService.getDetail(id);
        model.addAttribute("detail", review);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/detail";
    }

    /**
     * レビューの新規登録画面に遷移する.
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        // デフォルトで「表示する」チェックボックスがチェックされているように設定
        ReviewDto review = new ReviewDto();
        review.setEnable(Short.valueOf("1"));

        model.addAttribute("detail", review);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/add";
    }

    /**
     * レビューの新規登録を行う.
     * 
     * @param detail
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String creatte(Pageable pageable, @ModelAttribute ReviewDto detail, Model model, Principal principal) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Review review = new Review();
        review.setTitle(detail.getTitle());
        review.setShortComment(detail.getShortComment());
        review.setWatchedDate(detail.getWatchedDate());
        review.setWatchedTime(detail.getWatchedTime());
        review.setTheater(detail.getTheater());
        review.setScreen(detail.getScreen());
        review.setMainComment(detail.getMainComment());
        review.setEnable(detail.getEnable());
        review.setRating(detail.getRating());
        // ログイン情報から作成者を設定
        review.setCreatedBy(principal.getName());
        // ログイン情報から更新者を設定
        review.setUpdatedBy(principal.getName());

        reviewService.insert(review);

        Page<MonthlyListReviewItem> page= reviewService.getPage(pageable);
        // Page情報を設定
        model.addAttribute("page", page);
        // 遷移先を設定
        model.addAttribute("url", "/");
        // 表示情報を設定
        model.addAttribute("monthlyList", page.getContent());

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "index";
    }

    /**
     * 年月指定で検索処理を行う.
     * 
     * @param pageable
     * @param yearMonth
     * @param model
     * @return
     */
    @RequestMapping(value = "/yearmonthlist/{yearMonth}", method = RequestMethod.GET)
    public String yearMonthList(Pageable pageable, @PathVariable String yearMonth, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Page<MonthlyListReviewItem> page= reviewService.getMonthYearList(pageable, yearMonth);
        // Page情報を設定
        model.addAttribute("page", page);
        // 遷移先を設定
        model.addAttribute("url", "/yearmonthlist/" + yearMonth);
        // 表示情報を設定
        model.addAttribute("monthlyList", page.getContent());

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "index";
    }

    /**
     * レビューの編集画面に遷移する.
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Review review = reviewService.getDetail(id);
        model.addAttribute("detail", review);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "/edit";
    }

    /**
     * レビューの更新を行い、更新後の詳細情報を表示する.
     * 
     * @param detail
     * @param model
     * @param httpServletRequest
     * @param principal
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute ReviewDto detail, Model model, HttpServletRequest httpServletRequest, Principal principal) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        Review review = new Review();
        review.setId(detail.getId());
        review.setTitle(detail.getTitle());
        review.setShortComment(detail.getShortComment());
        review.setWatchedDate(detail.getWatchedDate());
        review.setWatchedTime(detail.getWatchedTime());
        review.setTheater(detail.getTheater());
        review.setScreen(detail.getScreen());
        review.setMainComment(detail.getMainComment());
        // 「表示する」チェックボックスがチェックされていなければ、値は送信されない
        review.setEnable((detail.getEnable() != null)?
            detail.getEnable() : Short.valueOf("0"));
        review.setRating(detail.getRating());
        // ログイン情報から更新者を設定
        review.setUpdatedBy(principal.getName());
        // 更新日時を設定
        review.setUpdated(LocalDateTime.now());

        reviewService.update(review);

        review = reviewService.getDetail(detail.getId());
        model.addAttribute("detail", review);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);
        
        return "/detail";
    }

    /**
     * 
     * ユーザ登録メニューの選択でユーザ登録画面に遷移する。
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        ReviewUserRegisterDto user =new ReviewUserRegisterDto();
        Map<String, String> authorities = new LinkedHashMap<String, String>();

        authorities.put("ROLE_ADMIN", "管理者ユーザ");
        authorities.put("ROLE_USER", "一般ユーザ");        
        user.setAuthorities(authorities);

        model.addAttribute("user", user);
        model.addAttribute("defaultAuth", "ROLE_USER");
        
        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/user";
    }

    /**
     * ユーザを登録.
     * 
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String register(@ModelAttribute ReviewUserRegisterDto user, Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        ReviewUser reviewUser = new ReviewUser();
        
        reviewUser.setUsername(user.getUsername());
        reviewUser.setPassword(hashedPassword);
        reviewUser.setAuthority(user.getAuthority());

        reviewUserService.insert(reviewUser);

        model.addAttribute("user", user);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/registered";
    }

    /**
     * ログイン画面への遷移を行う.
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        LoginDto login =new LoginDto();
        model.addAttribute("login", login);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/login";
    }

    /**
     * ログアウト画面への遷移を行う.
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {

        // 検索条件の入力エリアを生成
        ReviewSearchDto search = new ReviewSearchDto();
        model.addAttribute("search", search);

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/logout";
    }

    /**
     * レビューの検索処理を行う.
     * 
     * @param pageable
     * @param search
     * @param model
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String result(Pageable pageable, @ModelAttribute ReviewSearchDto search, Model model) {

        // 検索条件の入力エリアを引継ぎ
        model.addAttribute("search", search);
 
        Page<MonthlyListReviewItem> page= reviewService.getSearchList(pageable, search.getTitle());
        // Page情報を設定
        model.addAttribute("page", page);
        // 遷移先を設定
        model.addAttribute("url", "/search");
        // 表示情報を設定
        model.addAttribute("searchList", page.getContent());
        // 検索条件を設定
        model.addAttribute("searchTitle", search.getTitle());

        // アーカイブのリンクリストを生成
        List<String> yearMonthList = reviewService.getYearMonthList();
        model.addAttribute("yearMonthList", yearMonthList);

        return "/search";
    }

}
