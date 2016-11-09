package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MrDeng on 2016/7/19.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping(value = "/activity/test", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView("/activity/test");
    }

    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    public ModelAndView redirectActivity() {
        return new ModelAndView("/activity/list");
    }

    @RequestMapping(value = "/activity/group", method = RequestMethod.GET)
    public ModelAndView redirectGroupActivity() {
        return new ModelAndView("/activity/group");
    }

    @RequestMapping(value = "/appUser/allAppUser", method = RequestMethod.GET)
    public ModelAndView redirectAppUser() {
        return new ModelAndView("/appUser/allAppUser");
    }

    @RequestMapping(value = "/appUser/yrzAppUser", method = RequestMethod.GET)
    public ModelAndView redirectYrzAppUser() {
        return new ModelAndView("/appUser/yrzAppUserList");
    }

    @RequestMapping(value = "/appUser/dshAppUser", method = RequestMethod.GET)
    public ModelAndView redirectDshAppAuthUser() {
        return new ModelAndView("/appUser/dshAppUserList");
    }

    @RequestMapping(value = "/appUser/officialAppUser", method = RequestMethod.GET)
    public ModelAndView redirectOfficialAppUser() {
        return new ModelAndView("/appUser/officialAppUserList");
    }
    @RequestMapping(value = "/dynamic/dynamicIndex", method = RequestMethod.GET)
    public ModelAndView redirectDynamicIndex() {
        return new ModelAndView("/dynamic/dynamicIndex");
    }

    @RequestMapping(value = "/banner/activitybanner", method = RequestMethod.GET)
    public ModelAndView redirectActivityBanner() {
        return new ModelAndView("/banner/activitybanner");
    }

    @RequestMapping(value = "/banner/livebanner", method = RequestMethod.GET)
    public ModelAndView redirectLiveBanner() {
        return new ModelAndView("/banner/livebanner");
    }

    @RequestMapping(value = "/banner/circlebanner", method = RequestMethod.GET)
    public ModelAndView redirectCircleBanner() {
        return new ModelAndView("/banner/circlebanner");
    }

    @RequestMapping(value = "/banner/packagebanner", method = RequestMethod.GET)
    public ModelAndView redirectPackageBanner() {
        return new ModelAndView("/banner/packagebanner");
    }

    @RequestMapping(value = "/banner/dynamicbanner", method = RequestMethod.GET)
    public ModelAndView redirectDynamicBanner() {
        return new ModelAndView("/banner/dynamicbanner");
    }

    @RequestMapping(value = "/banner/userbanner", method = RequestMethod.GET)
    public ModelAndView redirectUserBanner() {
        return new ModelAndView("/banner/userbanner");
    }

    @RequestMapping(value = "/live/living", method = RequestMethod.GET)
    public ModelAndView redirectLive() {
        return new ModelAndView("/live/living");
    }

    @RequestMapping(value = "/shop/goods", method = RequestMethod.GET)
    public ModelAndView redirectGoods() {
        return new ModelAndView("/shop/goods");
    }

    @RequestMapping(value = "/shop/order", method = RequestMethod.GET)
    public ModelAndView redirectOrder() {
        return new ModelAndView("/shop/order");
    }

    @RequestMapping(value = "/sys/admin", method = RequestMethod.GET)
    public ModelAndView redirectAdmin() {
        return new ModelAndView("/sys/admin");
    }

    @RequestMapping(value = "/sys/roleList", method = RequestMethod.GET)
    public ModelAndView redirectRoleList() {
        return new ModelAndView("/sys/roleList");
    }

    @RequestMapping(value = "/message/messageIndex", method = RequestMethod.GET)
    public ModelAndView messageIndex() {
        return new ModelAndView("/message/messageIndex");
    }

    //圈子分类
    @RequestMapping(value = "/circle/circleclassify", method = RequestMethod.GET)
    public ModelAndView cirCleclassify() {
        return new ModelAndView("/circle/circleclassify");
    }

    //圈子列表
    @RequestMapping(value = "/circle/circlelist", method = RequestMethod.GET)
    public ModelAndView redirectCircleList() {
        return new ModelAndView("/circle/circlelist");
    }

    //圈子创建
    @RequestMapping(value = "/circle/circlepost", method = RequestMethod.GET)
    public ModelAndView redirectCirclePost() {
        return new ModelAndView("/circle/circlepost");
    }

    //攀岩管理
    @RequestMapping(value = "/climbing/banner", method = RequestMethod.GET)
    public ModelAndView redirectClimbingBanner() {
        return new ModelAndView("/climbing/banner");
    }
    @RequestMapping(value = "/climbing/equipment", method = RequestMethod.GET)
    public ModelAndView redirectClimbingEquipment() {
        return new ModelAndView("/climbing/equipment");
    }
    @RequestMapping(value = "/climbing/talentshow", method = RequestMethod.GET)
    public ModelAndView redirectClimbingTalentShow() {
        return new ModelAndView("/climbing/talentshow");
    }
    @RequestMapping(value = "/climbing/appointment", method = RequestMethod.GET)
    public ModelAndView redirectClimbingAppointment() {
        return new ModelAndView("/climbing/appointment");
    }
    @RequestMapping(value = "/climbing/venues", method = RequestMethod.GET)
    public ModelAndView redirectClimbingVenues() {
        return new ModelAndView("/climbing/venues");
    }
    /*社区*/
    @RequestMapping(value = "/community/dynamic", method = RequestMethod.GET)
    public ModelAndView redirectCommunityDynamic() {
        return new ModelAndView("/community/dynamic");
    }
    @RequestMapping(value = "/community/grades", method = RequestMethod.GET)
    public ModelAndView redirectCommunityGrades() {
        return new ModelAndView("/community/grades");
    }
    /*城市*/
    @RequestMapping(value = "/city/city", method = RequestMethod.GET)
    public ModelAndView redirectCityCity() {
        return new ModelAndView("/city/city");
    }
}
