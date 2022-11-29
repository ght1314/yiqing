layui.define(["jquery"], function (exports) {
	var jQuery = layui.jquery;
	(function ($) {
		/*!
        * jQuery Cookie Plugin v1.4.1
        * https://github.com/carhartl/jquery-cookie
        *
        * Copyright 2013 Klaus Hartl
        * Released under the MIT license
        */
		(function (factory) {
			if (typeof define === 'function' && define.amd) {
// AMD
				define(['static/lib/layui-v2.5.5/lay/modules/jquery'], factory);
			} else if (typeof exports === 'object') {
// CommonJS
				factory(require('static/lib/layui-v2.5.5/lay/modules/jquery'));
			} else {
// Browser globals
				factory(jQuery);
			}
		}(function ($) {
			var pluses = /\+/g;

			function encode(s) {
				return config.raw ? s : encodeURIComponent(s);
			}

			function decode(s) {
				return config.raw ? s : decodeURIComponent(s);
			}

			function stringifyCookieValue(value) {
				return encode(config.json ? JSON.stringify(value) : String(value));
			}

			function parseCookieValue(s) {
				if (s.indexOf('"') === 0) {
// This is a quoted cookie as according to RFC2068, unescape...
					s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
				}

				try {
// Replace server-side written pluses with spaces.
// If we can't decode the cookie, ignore it, it's unusable.
// If we can't parse the cookie, ignore it, it's unusable.
					s = decodeURIComponent(s.replace(pluses, ' '));
					return config.json ? JSON.parse(s) : s;
				} catch(e) {}
			}

			function read(s, converter) {
				var value = config.raw ? s : parseCookieValue(s);
				return $.isFunction(converter) ? converter(value) : value;
			}

			var config = $.cookie = function (key, value, options) {
// Write

				if (value !== undefined && !$.isFunction(value)) {
					options = $.extend({}, config.defaults, options);

					if (typeof options.expires === 'number') {
						var days = options.expires, t = options.expires = new Date();
						t.setTime(+t + days * 864e+5);
					}

					return (document.cookie = [
						encode(key), '=', stringifyCookieValue(value),
						options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
						options.path ? '; path=' + options.path : '',
						options.domain ? '; domain=' + options.domain : '',
						options.secure ? '; secure' : ''
					].join(''));
				}

// Read

				var result = key ? undefined : {};

// To prevent the for loop in the first place assign an empty array
// in case there are no cookies at all. Also prevents odd result when
// calling $.cookie().
				var cookies = document.cookie ? document.cookie.split('; ') : [];

				for (var i = 0, l = cookies.length; i < l; i++) {
					var parts = cookies[i].split('=');
					var name = decode(parts.shift());
					var cookie = parts.join('=');

					if (key && key === name) {
// If second argument (value) is a function it's a converter...
						result = read(cookie, value);
						break;
					}

// Prevent storing a cookie that we couldn't decode.
					if (!key && (cookie = read(cookie)) !== undefined) {
						result[name] = cookie;
					}
				}

				return result;
			};

			config.defaults = {};
			//read cookie
			$.getCookie = function (name) {
				var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
				if (arr != null) return unescape(arr[2]); return null;
			}

			//write cookie
			$.setCookie = function (name, value) {
				var exp = new Date();
				exp.setTime(exp.getTime() + 7 * 24 * 60 * 60 * 1000); //6天过期
				document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
				return true;
			};

			$.removeCookie = function (key, options) {
				if ($.cookie(key) === undefined) {
					return false;
				}

// Must not alter options, thus extending a fresh object...
				$.cookie(key, '', $.extend({}, options, { expires: -1 }));
				return !$.cookie(key);
			};

		}));

	})(jQuery);
	exports('cookie', null);
});