/**
 * Created by SubDong on 2016/3/21.
 *
 * 使用方法
 * var now = new Date();

 now.format("m/dd/yy");
 // Returns, e.g., 6/09/07

 // Can also be used as a standalone function
 dateFormat(now, "dddd, mmmm dS, yyyy, h:MM:ss TT");
 // Saturday, June 9th, 2007, 5:46:21 PM

 // You can use one of several named masks
 now.format("isoDateTime");
 // 2007-06-09T17:46:21

 // ...Or add your own
 dateFormat.masks.hammerTime = 'HH:MM! "Can\'t touch this!"';
 now.format("hammerTime");
 // 17:46! Can't touch this!

 // When using the standalone dateFormat function,
 // you can also provide the date as a string
 dateFormat("Jun 9 2007", "fullDate");
 // Saturday, June 9, 2007

 // Note that if you don't include the mask argument,
 // dateFormat.masks.default is used
 now.format();
 // Sat Jun 09 2007 17:46:21

 // And if you don't include the date argument,
 // the current date and time is used
 dateFormat();
 // Sat Jun 09 2007 17:46:22

 // You can also skip the date argument (as long as your mask doesn't
 // contain any numbers), in which case the current date/time is used
 dateFormat("longTime");
 // 5:46:22 PM EST

 // And finally, you can convert local time to UTC time. Either pass in
 // true as an additional argument (no argument skipping allowed in this case):
 dateFormat(now, "longTime", true);
 now.format("longTime", true);
 // Both lines return, e.g., 10:46:21 PM UTC

 // ...Or add the prefix "UTC:" to your mask.
 now.format("UTC:h:MM:ss TT Z");
 */
var dateFormat = function () {
    var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
        timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
        timezoneClip = /[^-+\dA-Z]/g,
        pad = function (val, len) {
            val = String(val);
            len = len || 2;
            while (val.length < len) val = "0" + val;
            return val;
        };

    // Regexes and supporting functions are cached through closure
    return function (date, mask, utc) {
        var dF = dateFormat;

        // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
        if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
            mask = date;
            date = undefined;
        }

        // Passing date through Date applies Date.parse, if necessary
        date = date ? new Date(date) : new Date;
        if (isNaN(date)) throw SyntaxError("invalid date");

        mask = String(dF.masks[mask] || mask || dF.masks["default"]);

        // Allow setting the utc argument via the mask
        if (mask.slice(0, 4) == "UTC:") {
            mask = mask.slice(4);
            utc = true;
        }

        var _ = utc ? "getUTC" : "get",
            d = date[_ + "Date"](),
            D = date[_ + "Day"](),
            m = date[_ + "Month"](),
            y = date[_ + "FullYear"](),
            H = date[_ + "Hours"](),
            M = date[_ + "Minutes"](),
            s = date[_ + "Seconds"](),
            L = date[_ + "Milliseconds"](),
            o = utc ? 0 : date.getTimezoneOffset(),
            flags = {
                d:    d,
                dd:   pad(d),
                ddd:  dF.i18n.dayNames[D],
                dddd: dF.i18n.dayNames[D + 7],
                m:    m + 1,
                mm:   pad(m + 1),
                mmm:  dF.i18n.monthNames[m],
                mmmm: dF.i18n.monthNames[m + 12],
                yy:   String(y).slice(2),
                yyyy: y,
                h:    H % 12 || 12,
                hh:   pad(H % 12 || 12),
                H:    H,
                HH:   pad(H),
                M:    M,
                MM:   pad(M),
                s:    s,
                ss:   pad(s),
                l:    pad(L, 3),
                L:    pad(L > 99 ? Math.round(L / 10) : L),
                t:    H < 12 ? "a"  : "p",
                tt:   H < 12 ? "am" : "pm",
                T:    H < 12 ? "A"  : "P",
                TT:   H < 12 ? "AM" : "PM",
                Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
                o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
                S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
            };

        return mask.replace(token, function ($0) {
            return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
        });
    };
}();

// Some common format strings
dateFormat.masks = {
    "default":      "ddd mmm dd yyyy HH:MM:ss",
    shortDate:      "m/d/yy",
    mediumDate:     "mmm d, yyyy",
    longDate:       "mmmm d, yyyy",
    fullDate:       "dddd, mmmm d, yyyy",
    shortTime:      "h:MM TT",
    mediumTime:     "h:MM:ss TT",
    longTime:       "h:MM:ss TT Z",
    isoDate:        "yyyy-mm-dd",
    isoTime:        "HH:MM:ss",
    isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
    isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
dateFormat.i18n = {
    dayNames: [
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    ],
    monthNames: [
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    ]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
    return dateFormat(this, mask, utc);
};

/**
 * 毫秒转换友好的显示格式
 * 输出格式：21小时前
 * @param  {[type]} time [description]
 * @return {[type]}      [description]
 */
function dateStr(date){
    //获取js 时间戳
    var time=new Date().getTime();
    //去掉 js ，与php 时间戳保持一致时间戳后三位
    time=parseInt((time-date)/1000);
    //存储转换值
    var s;
    if(time<60*10){//十分钟内
        return '刚刚';
    }else if((time<60*60)&&(time>=60*10)){
        //超过十分钟少于1小时
        s = Math.floor(time/60);
        return  s+"分钟前";
    }else if((time<60*60*24)&&(time>=60*60)){
        //超过1小时少于24小时
        s = Math.floor(time/60/60);
        return  s+"小时前";
    }else if((time<60*60*24*3)&&(time>=60*60*24)){
        //超过1天少于3天内
        s = Math.floor(time/60/60/24);
        return s+"天前";
    }else{
        //超过3天
        return new Date(date).format("yyyy-mm-dd HH:MM:ss")
        //var date= new Date(parseInt(date) );
        return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
    }
}