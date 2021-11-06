package com.tt.fly.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author molly
 * @Date 2021/11/05
 */
public class Response {
        public static Object ok() {
            Map<String, Object> obj = new HashMap<String, Object>(1);
            obj.put("errno", 0);
            obj.put("errmsg", "成功");
            return obj;
        }

        public static Object ok(Object data) {
            Map<String, Object> obj = new HashMap<String, Object>(1);
            obj.put("errno", 0);
            obj.put("errmsg", "成功");
            obj.put("data", data);
            return obj;
        }

        public static Object okList(List list) {
            Map<String, Object> data = new HashMap<>(1);
            data.put("list", list);
            data.put("total", list.size());
            data.put("page", 1);
            data.put("limit", list.size());
            data.put("pages", 1);
            return ok(data);
        }

        public static Object okList(List list, List pagedList) {
            Map<String, Object> data = new HashMap<String, Object>(1);
            data.put("list", list);
            data.put("total", pagedList.size());
            data.put("page", 1);
            data.put("limit", pagedList.size());
            data.put("pages", 1);

            return ok(data);
        }

        public static Object fail() {
            Map<String, Object> obj = new HashMap<String, Object>(1);
            obj.put("errno", -1);
            obj.put("errmsg", "错误");
            return obj;
        }

        public static Object fail(int errno, String errmsg) {
            Map<String, Object> obj = new HashMap<String, Object>(1);
            obj.put("errno", errno);
            obj.put("errmsg", errmsg);
            return obj;
        }

        public static Object badArgument() {
            return fail(401, "参数错误");
        }

        public static Object systemError() {
            return fail(501, "系统错误");
        }
}
