(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["dashboard"], {
  "070f": function (t, e, a) {
    "use strict";
    a.r(e);
    var r = function () {
        var t = this, e = t.$createElement, a = t._self._c || e;
        return a("div", {staticClass: "addBrand-container"}, [a("HeadLable", {
          attrs: {
            title: t.title,
            goback: !0
          }
        }), a("div", {staticClass: "container"}, [a("el-form", {
          ref: "ruleForm",
          staticClass: "demo-ruleForm",
          attrs: {model: t.ruleForm, rules: t.rules, inline: !1, "label-width": "180px"}
        }, [a("el-form-item", {
          attrs: {
            label: "账号:",
            prop: "username"
          }
        }, [a("el-input", {
          attrs: {placeholder: "请输入账号", maxlength: "20"},
          model: {
            value: t.ruleForm.username, callback: function (e) {
              t.$set(t.ruleForm, "username", e)
            }, expression: "ruleForm.username"
          }
        })], 1), a("el-form-item", {
          attrs: {
            label: "员工姓名:",
            prop: "name"
          }
        }, [a("el-input", {
          attrs: {placeholder: "请输入员工姓名", maxlength: "12"},
          model: {
            value: t.ruleForm.name, callback: function (e) {
              t.$set(t.ruleForm, "name", e)
            }, expression: "ruleForm.name"
          }
        })], 1), a("el-form-item", {
          attrs: {
            label: "手机号:",
            prop: "phone"
          }
        }, [a("el-input", {
          attrs: {placeholder: "请输入手机号", maxlength: "11"},
          model: {
            value: t.ruleForm.phone, callback: function (e) {
              t.$set(t.ruleForm, "phone", e)
            }, expression: "ruleForm.phone"
          }
        })], 1), a("el-form-item", {
          attrs: {
            label: "性别:",
            prop: "sex"
          }
        }, [a("el-radio-group", {
          model: {
            value: t.ruleForm.sex, callback: function (e) {
              t.$set(t.ruleForm, "sex", e)
            }, expression: "ruleForm.sex"
          }
        }, [a("el-radio", {attrs: {label: "男"}}), a("el-radio", {attrs: {label: "女"}})], 1)], 1), a("el-form-item", {
          staticClass: "idNumber",
          attrs: {label: "身份证号:", prop: "idNumber"}
        }, [a("el-input", {
          attrs: {placeholder: "请输入身份证号", maxlength: "20"},
          model: {
            value: t.ruleForm.idNumber, callback: function (e) {
              t.$set(t.ruleForm, "idNumber", e)
            }, expression: "ruleForm.idNumber"
          }
        })], 1), a("div", {staticClass: "subBox address"}, [a("el-button", {
          on: {
            click: function () {
              return t.$router.push("/employee")
            }
          }
        }, [t._v("\n          取消\n        ")]), a("el-button", {
          class: {continue: "add" === t.actionType},
          attrs: {type: "primary"},
          on: {
            click: function (e) {
              return t.submitForm("ruleForm", !1)
            }
          }
        }, [t._v("\n          保存\n        ")]), "add" == t.actionType ? a("el-button", {
          attrs: {type: "primary"},
          on: {
            click: function (e) {
              return t.submitForm("ruleForm", !0)
            }
          }
        }, [t._v("\n          保存并继续添加\n        ")]) : t._e()], 1)], 1)], 1)], 1)
      }, s = [], n = (a("8e6e"), a("ac6a"), a("456d"), a("bd86")), i = (a("96cf"), a("3b8d")), o = a("d225"),
      c = a("b0b4"), l = a("308d"), u = a("6bb5"), d = a("4e2b"), p = a("9ab4"), v = a("60a3"), b = a("d666"),
      m = a("63f6");

    function f(t, e) {
      var a = Object.keys(t);
      if (Object.getOwnPropertySymbols) {
        var r = Object.getOwnPropertySymbols(t);
        e && (r = r.filter((function (e) {
          return Object.getOwnPropertyDescriptor(t, e).enumerable
        }))), a.push.apply(a, r)
      }
      return a
    }

    function h(t) {
      for (var e = 1; e < arguments.length; e++) {
        var a = null != arguments[e] ? arguments[e] : {};
        e % 2 ? f(Object(a), !0).forEach((function (e) {
          Object(n["a"])(t, e, a[e])
        })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(t, Object.getOwnPropertyDescriptors(a)) : f(Object(a)).forEach((function (e) {
          Object.defineProperty(t, e, Object.getOwnPropertyDescriptor(a, e))
        }))
      }
      return t
    }

    var g = function (t) {
      function e() {
        var t;
        return Object(o["a"])(this, e), t = Object(l["a"])(this, Object(u["a"])(e).apply(this, arguments)), t.title = "添加员工", t.actionType = "", t.ruleForm = {
          name: "",
          phone: "",
          sex: "男",
          idNumber: "",
          username: ""
        }, t
      }

      return Object(d["a"])(e, t), Object(c["a"])(e, [{
        key: "isCellPhone", value: function (t) {
          return !!/^1(3|4|5|6|7|8)\d{9}$/.test(t)
        }
      }, {
        key: "checkphone", value: function (t, e, a) {
          "" == e ? a(new Error("请输入手机号")) : this.isCellPhone(e) ? a() : a(new Error("请输入正确的手机号!"))
        }
      }, {
        key: "validID", value: function (t, e, a) {
          var r = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
          "" == e ? a(new Error("请输入身份证号码")) : r.test(e) ? a() : a(new Error("身份证号码不正确"))
        }
      }, {
        key: "created", value: function () {
          this.actionType = this.$route.query.id ? "edit" : "add", this.$route.query.id && (this.title = "修改员工信息", this.init())
        }
      }, {
        key: "init", value: function () {
          var t = Object(i["a"])(regeneratorRuntime.mark((function t() {
            var e, a = this;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  e = this.$route.query.id, Object(m["f"])(e).then((function (t) {
                    1 === t.data.code ? (a.ruleForm = t.data.data, a.ruleForm.sex = "0" === t.data.data.sex ? "女" : "男") : a.$message.error(t.data.msg)
                  }));
                case 2:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e() {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "submitForm", value: function (t, e) {
          var a = this;
          this.$refs[t].validate((function (t) {
            if (!t) return !1;
            if ("add" === a.actionType) {
              var r = h({}, a.ruleForm, {sex: "女" === a.ruleForm.sex ? "0" : "1"});
              Object(m["a"])(r).then((function (t) {
                1 === t.data.code ? (a.$message.success("员工添加成功！"), e ? a.ruleForm = {
                  username: "",
                  name: "",
                  phone: "",
                  sex: "男",
                  idNumber: ""
                } : a.$router.push({path: "/employee"})) : a.$message.error(t.data.msg)
              })).catch((function () {
              }))
            } else {
              var s = h({}, a.ruleForm, {sex: "女" === a.ruleForm.sex ? "0" : "1"});
              Object(m["b"])(s).then((function (t) {
                1 === t.data.code ? (a.$message.success("员工信息修改成功！"), a.$router.push({path: "/employee"})) : a.$message.error(t.data.msg)
              })).catch((function () {
              }))
            }
          }))
        }
      }, {
        key: "rules", get: function () {
          return {
            name: [{
              required: !0, validator: function (t, e, a) {
                e ? a() : a(new Error("请输入员工姓名"))
              }, trigger: "blur"
            }],
            username: [{
              required: !0, validator: function (t, e, a) {
                if (e) {
                  var r = /^([a-z]|[0-9]){3,20}$/;
                  r.test(e) ? a() : a(new Error("账号输入不符，请输入3-20个字符"))
                } else a(new Error("请输入账号"))
              }, trigger: "blur"
            }],
            phone: [{required: !0, validator: this.checkphone, trigger: "blur"}],
            idNumber: [{required: !0, validator: this.validID, trigger: "blur"}]
          }
        }
      }]), e
    }(v["c"]);
    g = Object(p["a"])([Object(v["a"])({name: "addShop", components: {HeadLable: b["a"]}})], g);
    var O = g, _ = O, y = (a("42b7"), a("2877")), j = Object(y["a"])(_, r, s, !1, null, "0bbe0753", null);
    e["default"] = j.exports
  }, 2235: function (t, e, a) {
    t.exports = a.p + "img/search_table_empty.e769fc3e.png"
  }, "2e08": function (t, e, a) {
    var r = a("9def"), s = a("9744"), n = a("be13");
    t.exports = function (t, e, a, i) {
      var o = String(n(t)), c = o.length, l = void 0 === a ? " " : String(a), u = r(e);
      if (u <= c || "" == l) return o;
      var d = u - c, p = s.call(l, Math.ceil(d / l.length));
      return p.length > d && (p = p.slice(0, d)), i ? p + o : o + p
    }
  }, "38b8f": function (t, e, a) {
    t.exports = a.p + "img/table_empty.885371bc.png"
  }, "3b2b": function (t, e, a) {
    var r = a("7726"), s = a("5dbc"), n = a("86cc").f, i = a("9093").f, o = a("aae3"), c = a("0bfb"), l = r.RegExp,
      u = l, d = l.prototype, p = /a/g, v = /a/g, b = new l(p) !== p;
    if (a("9e1e") && (!b || a("79e5")((function () {
      return v[a("2b4c")("match")] = !1, l(p) != p || l(v) == v || "/a/i" != l(p, "i")
    })))) {
      l = function (t, e) {
        var a = this instanceof l, r = o(t), n = void 0 === e;
        return !a && r && t.constructor === l && n ? t : s(b ? new u(r && !n ? t.source : t, e) : u((r = t instanceof l) ? t.source : t, r && n ? c.call(t) : e), a ? this : d, l)
      };
      for (var m = function (t) {
        t in l || n(l, t, {
          configurable: !0, get: function () {
            return u[t]
          }, set: function (e) {
            u[t] = e
          }
        })
      }, f = i(u), h = 0; f.length > h;) m(f[h++]);
      d.constructor = l, l.prototype = d, a("2aba")(r, "RegExp", l)
    }
    a("7a56")("RegExp")
  }, "42b7": function (t, e, a) {
    "use strict";
    var r = a("c0d1"), s = a.n(r);
    s.a
  }, 4455: function (t, e, a) {
    "use strict";
    var r = a("d153"), s = a.n(r);
    s.a
  }, "560a": function (t, e, a) {
    "use strict";
    a.d(e, "c", (function () {
      return o
    })), a.d(e, "h", (function () {
      return c
    })), a.d(e, "b", (function () {
      return l
    })), a.d(e, "a", (function () {
      return u
    })), a.d(e, "f", (function () {
      return d
    })), a.d(e, "e", (function () {
      return p
    })), a.d(e, "g", (function () {
      return v
    })), a.d(e, "d", (function () {
      return b
    }));
    a("8e6e"), a("ac6a"), a("456d");
    var r = a("bd86"), s = a("b32d");

    function n(t, e) {
      var a = Object.keys(t);
      if (Object.getOwnPropertySymbols) {
        var r = Object.getOwnPropertySymbols(t);
        e && (r = r.filter((function (e) {
          return Object.getOwnPropertyDescriptor(t, e).enumerable
        }))), a.push.apply(a, r)
      }
      return a
    }

    function i(t) {
      for (var e = 1; e < arguments.length; e++) {
        var a = null != arguments[e] ? arguments[e] : {};
        e % 2 ? n(Object(a), !0).forEach((function (e) {
          Object(r["a"])(t, e, a[e])
        })) : Object.getOwnPropertyDescriptors ? Object.defineProperties(t, Object.getOwnPropertyDescriptors(a)) : n(Object(a)).forEach((function (e) {
          Object.defineProperty(t, e, Object.getOwnPropertyDescriptor(a, e))
        }))
      }
      return t
    }

    var o = function (t) {
      return Object(s["a"])({url: "/order/conditionSearch", method: "get", params: t})
    }, c = function (t) {
      return Object(s["a"])({url: "/order/details/".concat(t.orderId), method: "get"})
    }, l = function (t) {
      return Object(s["a"])({url: "/order/delivery/".concat(t.id), method: "put"})
    }, u = function (t) {
      return Object(s["a"])({url: "/order/complete/".concat(t.id), method: "put"})
    }, d = function (t) {
      return Object(s["a"])({url: "/order/cancel", method: "put", data: i({}, t)})
    }, p = function (t) {
      return Object(s["a"])({url: "/order/confirm", method: "put", data: i({}, t)})
    }, v = function (t) {
      return Object(s["a"])({url: "/order/rejection", method: "put", data: i({}, t)})
    }, b = function (t) {
      return Object(s["a"])({url: "/order/statistics", method: "get"})
    }
  }, "5dbc": function (t, e, a) {
    var r = a("d3f4"), s = a("8b97").set;
    t.exports = function (t, e, a) {
      var n, i = e.constructor;
      return i !== a && "function" == typeof i && (n = i.prototype) !== a.prototype && r(n) && s && s(t, n), t
    }
  }, "79f6": function (t, e, a) {
    "use strict";
    a.d(e, "c", (function () {
      return s
    })), a.d(e, "e", (function () {
      return n
    })), a.d(e, "f", (function () {
      return i
    })), a.d(e, "b", (function () {
      return o
    })), a.d(e, "h", (function () {
      return c
    })), a.d(e, "i", (function () {
      return l
    })), a.d(e, "d", (function () {
      return u
    })), a.d(e, "g", (function () {
      return d
    })), a.d(e, "a", (function () {
      return p
    }));
    var r = a("b32d"), s = function () {
      return Object(r["a"])({url: "/workspace/overviewOrders", method: "get"})
    }, n = function () {
      return Object(r["a"])({url: "/workspace/overviewDishes", method: "get"})
    }, i = function () {
      return Object(r["a"])({url: "/workspace/overviewSetmeals", method: "get"})
    }, o = function () {
      return Object(r["a"])({url: "/workspace/businessData", method: "get"})
    }, c = function (t) {
      return Object(r["a"])({url: "/report/turnoverStatistics", method: "get", params: t})
    }, l = function (t) {
      return Object(r["a"])({url: "/report/userStatistics", method: "get", params: t})
    }, u = function (t) {
      return Object(r["a"])({url: "/report/ordersStatistics", method: "get", params: t})
    }, d = function (t) {
      return Object(r["a"])({url: "/report/top10", method: "get", params: t})
    };

    function p() {
      return Object(r["a"])({url: "/report/export", method: "get", responseType: "blob"})
    }
  }, 8830: function (t, e, a) {
  }, "8b97": function (t, e, a) {
    var r = a("d3f4"), s = a("cb7c"), n = function (t, e) {
      if (s(t), !r(e) && null !== e) throw TypeError(e + ": can't set as prototype!")
    };
    t.exports = {
      set: Object.setPrototypeOf || ("__proto__" in {} ? function (t, e, r) {
        try {
          r = a("9b43")(Function.call, a("11e9").f(Object.prototype, "__proto__").set, 2), r(t, []), e = !(t instanceof Array)
        } catch (s) {
          e = !0
        }
        return function (t, a) {
          return n(t, a), e ? t.__proto__ = a : r(t, a), t
        }
      }({}, !1) : void 0), check: n
    }
  }, 9406: function (t, e, a) {
    "use strict";
    a.r(e);
    var r = function () {
        var t = this, e = t.$createElement, a = t._self._c || e;
        return a("div", {staticClass: "dashboard-container home"}, [a("Overview", {attrs: {overviewData: t.overviewData}}), a("Orderview", {attrs: {orderviewData: t.orderviewData}}), a("div", {staticClass: "homeMain"}, [a("CuisineStatistics", {attrs: {dishesData: t.dishesData}}), a("SetMealStatistics", {attrs: {setMealData: t.setMealData}})], 1), a("OrderList", {
          attrs: {"order-statics": t.orderStatics},
          on: {getOrderListBy3Status: t.getOrderListBy3Status}
        })], 1)
      }, s = [], n = (a("96cf"), a("3b8d")), i = a("d225"), o = a("b0b4"), c = a("308d"), l = a("6bb5"), u = a("4e2b"),
      d = a("9ab4"), p = a("60a3"), v = a("79f6"), b = a("560a"), m = function () {
        var t = this, e = t.$createElement, a = t._self._c || e;
        return a("div", {staticClass: "container"}, [a("h2", {staticClass: "homeTitle"}, [t._v("\n    今日数据"), a("i", [t._v(t._s(t.days[1]))]), a("span", [a("router-link", {attrs: {to: "statistics"}}, [t._v("详细数据")])], 1)]), a("div", {staticClass: "overviewBox"}, [a("ul", [a("li", [a("p", {staticClass: "tit"}, [t._v("营业额")]), a("p", {staticClass: "num"}, [t._v("¥ " + t._s(t.overviewData.turnover))])]), a("li", [a("p", {staticClass: "tit"}, [t._v("有效订单")]), a("p", {staticClass: "num"}, [t._v(t._s(t.overviewData.validOrderCount))])]), a("li", [a("p", {staticClass: "tit"}, [t._v("订单完成率")]), a("p", {staticClass: "num"}, [t._v("\n          " + t._s((100 * t.overviewData.orderCompletionRate).toFixed(0)) + "%\n        ")])]), a("li", [a("p", {staticClass: "tit"}, [t._v("平均客单价")]), a("p", {staticClass: "num"}, [t._v("¥ " + t._s(t.overviewData.unitPrice))])]), a("li", [a("p", {staticClass: "tit"}, [t._v("新增用户")]), a("p", {staticClass: "num"}, [t._v(t._s(t.overviewData.newUsers))])])])])])
      }, f = [], h = a("d9cf"), g = function (t) {
        function e() {
          return Object(i["a"])(this, e), Object(c["a"])(this, Object(l["a"])(e).apply(this, arguments))
        }

        return Object(u["a"])(e, t), Object(o["a"])(e, [{
          key: "days", get: function () {
            return Object(h["b"])()
          }
        }]), e
      }(p["c"]);
    Object(d["a"])([Object(p["b"])()], g.prototype, "overviewData", void 0), g = Object(d["a"])([Object(p["a"])({name: "Overview"})], g);
    var O = g, _ = O, y = a("2877"), j = Object(y["a"])(_, m, f, !1, null, null, null), C = j.exports, w = function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("div", {staticClass: "container"}, [a("h2", {staticClass: "homeTitle"}, [t._v("\n    订单管理"), a("i", [t._v(t._s(t.days[1]))]), a("span", [a("router-link", {attrs: {to: "/order"}}, [t._v("订单明细")])], 1)]), a("div", {staticClass: "orderviewBox"}, [a("ul", [a("li", [t._m(0), a("span", {staticClass: "num tip"}, [a("router-link", {attrs: {to: "/order?status=2"}}, [t._v(t._s(t.orderviewData.waitingOrders))])], 1)]), a("li", [t._m(1), a("span", {staticClass: "num tip"}, [a("router-link", {attrs: {to: "/order?status=3"}}, [t._v(t._s(t.orderviewData.deliveredOrders))])], 1)]), a("li", [t._m(2), a("span", {staticClass: "num"}, [a("router-link", {attrs: {to: "/order?status=5"}}, [t._v(t._s(t.orderviewData.completedOrders))])], 1)]), a("li", [t._m(3), a("span", {staticClass: "num"}, [a("router-link", {attrs: {to: "/order?status=6"}}, [t._v(t._s(t.orderviewData.cancelledOrders))])], 1)]), a("li", [t._m(4), a("span", {staticClass: "num"}, [a("router-link", {attrs: {to: "/order"}}, [t._v(t._s(t.orderviewData.allOrders))])], 1)])])])])
    }, D = [function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-waiting"}), t._v("待接单")])
    }, function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-staySway"}), t._v("待派送")])
    }, function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-complete"}), t._v("已完成")])
    }, function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-cancel"}), t._v("已取消")])
    }, function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-all"}), t._v("全部订单")])
    }], k = function (t) {
      function e() {
        return Object(i["a"])(this, e), Object(c["a"])(this, Object(l["a"])(e).apply(this, arguments))
      }

      return Object(u["a"])(e, t), Object(o["a"])(e, [{
        key: "days", get: function () {
          return Object(h["b"])()
        }
      }]), e
    }(p["c"]);
    Object(d["a"])([Object(p["b"])()], k.prototype, "orderviewData", void 0), k = Object(d["a"])([Object(p["a"])({name: "Orderview"})], k);
    var x = k, S = x, F = Object(y["a"])(S, w, D, !1, null, null, null), Y = F.exports, $ = function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("div", {staticClass: "container"}, [a("h2", {staticClass: "homeTitle"}, [t._v("\n    菜品总览"), a("span", [a("router-link", {attrs: {to: "dish"}}, [t._v("菜品管理")])], 1)]), a("div", {staticClass: "orderviewBox"}, [a("ul", [a("li", [t._m(0), a("span", {staticClass: "num"}, [t._v(t._s(t.dishesData.sold))])]), a("li", [t._m(1), a("span", {staticClass: "num"}, [t._v(t._s(t.dishesData.discontinued))])]), a("li", {staticClass: "add"}, [a("router-link", {attrs: {to: "/dish/add"}}, [a("i"), a("p", [t._v("新增菜品")])])], 1)])])])
    }, T = [function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-open"}), t._v("已启售")])
    }, function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-stop"}), t._v("已停售")])
    }], A = function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("div", [a("div", {staticClass: "container homecon"}, [a("h2", {staticClass: "homeTitle homeTitleBtn"}, [t._v("\n      订单信息\n      "), a("ul", {staticClass: "conTab"}, t._l(t.tabList, (function (e, r) {
        return a("li", {
          key: r, class: t.activeIndex === r ? "active" : "", on: {
            click: function (e) {
              return t.handleClass(r)
            }
          }
        }, [a("el-badge", {
          staticClass: "item",
          class: e.num >= 10 ? "badgeW" : "",
          attrs: {value: e.num > 99 ? "99+" : e.num, hidden: !([2, 3].includes(e.value) && e.num)}
        }, [t._v(t._s(e.label))])], 1)
      })), 0)]), a("div", {}, [t.orderData.length > 0 ? a("div", [a("el-table", {
        staticClass: "tableBox",
        staticStyle: {width: "100%"},
        attrs: {data: t.orderData, stripe: ""},
        on: {"row-click": t.handleTable}
      }, [a("el-table-column", {
        attrs: {
          prop: "number",
          label: "订单号"
        }
      }), a("el-table-column", {
        attrs: {label: "订单菜品"}, scopedSlots: t._u([{
          key: "default", fn: function (e) {
            return [a("div", {staticClass: "ellipsisHidden"}, [a("el-popover", {
              attrs: {
                placement: "top-start",
                title: "",
                width: "200",
                trigger: "hover",
                content: e.row.orderDishes
              }
            }, [a("span", {attrs: {slot: "reference"}, slot: "reference"}, [t._v(t._s(e.row.orderDishes))])])], 1)]
          }
        }], null, !1, 2845630214)
      }), a("el-table-column", {
        attrs: {label: "地址", "class-name": 2 === t.dialogOrderStatus ? "address" : ""},
        scopedSlots: t._u([{
          key: "default", fn: function (e) {
            return [a("div", {staticClass: "ellipsisHidden"}, [a("el-popover", {
              attrs: {
                placement: "top-start",
                title: "",
                width: "200",
                trigger: "hover",
                content: e.row.address
              }
            }, [a("span", {attrs: {slot: "reference"}, slot: "reference"}, [t._v(t._s(e.row.address))])])], 1)]
          }
        }], null, !1, 3554143750)
      }), a("el-table-column", {
        attrs: {
          prop: "estimatedDeliveryTime",
          label: "预计送达时间",
          sortable: "",
          "class-name": "orderTime",
          "min-width": "130"
        }
      }), a("el-table-column", {
        attrs: {
          prop: "amount",
          label: "实收金额"
        }
      }), a("el-table-column", {
        attrs: {label: "备注"}, scopedSlots: t._u([{
          key: "default", fn: function (e) {
            return [a("div", {staticClass: "ellipsisHidden"}, [a("el-popover", {
              attrs: {
                placement: "top-start",
                title: "",
                width: "200",
                trigger: "hover",
                content: e.row.remark
              }
            }, [a("span", {attrs: {slot: "reference"}, slot: "reference"}, [t._v(t._s(e.row.remark))])])], 1)]
          }
        }], null, !1, 3505279526)
      }), 3 === t.status ? a("el-table-column", {
        attrs: {
          prop: "tablewareNumber",
          label: "餐具数量",
          "min-width": "80",
          align: "center"
        }
      }) : t._e(), a("el-table-column", {
        attrs: {
          label: "操作",
          align: "center",
          "class-name": 0 === t.dialogOrderStatus ? "operate" : "otherOperate",
          "min-width": [2, 3].includes(t.dialogOrderStatus) ? 130 : [0].includes(t.dialogOrderStatus) ? 140 : "auto"
        }, scopedSlots: t._u([{
          key: "default", fn: function (e) {
            var r = e.row;
            return [a("div", {staticClass: "before"}, [2 === r.status ? a("el-button", {
              staticClass: "blueBug",
              attrs: {type: "text"},
              on: {
                click: function (e) {
                  t.orderAccept(r, e), t.isTableOperateBtn = !0
                }
              }
            }, [t._v("\n                  接单\n                ")]) : t._e(), 3 === r.status ? a("el-button", {
              staticClass: "blueBug",
              attrs: {type: "text"},
              on: {
                click: function (e) {
                  return t.cancelOrDeliveryOrComplete(3, r.id, e)
                }
              }
            }, [t._v("\n                  派送\n                ")]) : t._e()], 1), a("div", {staticClass: "middle"}, [2 === r.status ? a("el-button", {
              staticClass: "delBut",
              attrs: {type: "text"},
              on: {
                click: function (e) {
                  t.orderReject(r, e), t.isTableOperateBtn = !0
                }
              }
            }, [t._v("\n                  拒单\n                ")]) : t._e(), [1, 3, 4, 5].includes(r.status) ? a("el-button", {
              staticClass: "delBut",
              attrs: {type: "text"},
              on: {
                click: function (e) {
                  return t.cancelOrder(r, e)
                }
              }
            }, [t._v("\n                  取消\n                ")]) : t._e()], 1), a("div", {staticClass: "after"}, [a("el-button", {
              staticClass: "blueBug non",
              attrs: {type: "text"},
              on: {
                click: function (e) {
                  return t.goDetail(r.id, r.status, r, e)
                }
              }
            }, [t._v("\n                  查看\n                ")])], 1)]
          }
        }], null, !1, 3413524294)
      })], 1)], 1) : a("Empty", {attrs: {"is-search": t.isSearch}}), t.counts > 10 ? a("el-pagination", {
        staticClass: "pageList",
        attrs: {
          "page-sizes": [10, 20, 30, 40],
          "page-size": t.pageSize,
          layout: "total, sizes, prev, pager, next, jumper",
          total: t.counts
        },
        on: {"size-change": t.handleSizeChange, "current-change": t.handleCurrentChange}
      }) : t._e()], 1)]), a("el-dialog", {
        staticClass: "order-dialog",
        attrs: {title: "订单信息", visible: t.dialogVisible, width: "53%", "before-close": t.handleClose},
        on: {
          "update:visible": function (e) {
            t.dialogVisible = e
          }
        }
      }, [a("el-scrollbar", {staticStyle: {height: "100%"}}, [a("div", {staticClass: "order-top"}, [a("div", [a("div", {staticStyle: {display: "inline-block"}}, [a("label", {staticStyle: {"font-size": "16px"}}, [t._v("订单号：")]), a("div", {staticClass: "order-num"}, [t._v("\n              " + t._s(t.diaForm.number) + "\n            ")])]), a("div", {
        staticClass: "order-status",
        class: {status3: [3, 4].includes(t.dialogOrderStatus)},
        staticStyle: {display: "inline-block"}
      }, [t._v("\n            " + t._s(t.orderList.filter((function (e) {
        return e.value === t.dialogOrderStatus
      }))[0].label) + "\n          ")])]), a("p", [a("label", [t._v("下单时间：")]), t._v(t._s(t.diaForm.orderTime))])]), a("div", {staticClass: "order-middle"}, [a("div", {staticClass: "user-info"}, [a("div", {staticClass: "user-info-box"}, [a("div", {staticClass: "user-name"}, [a("label", [t._v("用户名：")]), a("span", [t._v(t._s(t.diaForm.consignee))])]), a("div", {staticClass: "user-phone"}, [a("label", [t._v("手机号：")]), a("span", [t._v(t._s(t.diaForm.phone))])]), [2, 3, 4, 5].includes(t.dialogOrderStatus) ? a("div", {staticClass: "user-getTime"}, [a("label", [t._v(t._s(5 === t.dialogOrderStatus ? "送达时间：" : "预计送达时间："))]), a("span", [t._v(t._s(5 === t.dialogOrderStatus ? t.diaForm.deliveryTime : t.diaForm.estimatedDeliveryTime))])]) : t._e(), a("div", {staticClass: "user-address"}, [a("label", [t._v("地址：")]), a("span", [t._v(t._s(t.diaForm.address))])])]), a("div", {
        staticClass: "user-remark",
        class: {orderCancel: 6 === t.dialogOrderStatus}
      }, [a("div", [t._v(t._s(6 === t.dialogOrderStatus ? "取消原因" : "备注"))]), a("span", [t._v(t._s(6 === t.dialogOrderStatus ? t.diaForm.cancelReason || t.diaForm.rejectionReason : t.diaForm.remark))])])]), a("div", {staticClass: "dish-info"}, [a("div", {staticClass: "dish-label"}, [t._v("菜品")]), a("div", {staticClass: "dish-list"}, t._l(t.diaForm.orderDetailList, (function (e, r) {
        return a("div", {
          key: r,
          staticClass: "dish-item"
        }, [a("span", {staticClass: "dish-name"}, [t._v(t._s(e.name))]), a("span", {staticClass: "dish-num"}, [t._v("x" + t._s(e.number))]), a("span", {staticClass: "dish-price"}, [t._v("￥" + t._s(e.amount ? e.amount.toFixed(2) : ""))])])
      })), 0), a("div", {staticClass: "dish-all-amount"}, [a("label", [t._v("菜品小计")]), a("span", [t._v("￥" + t._s((t.diaForm.amount - 6 - t.diaForm.packAmount).toFixed(2)))])])])]), a("div", {staticClass: "order-bottom"}, [a("div", {staticClass: "amount-info"}, [a("div", {staticClass: "amount-label"}, [t._v("费用")]), a("div", {staticClass: "amount-list"}, [a("div", {staticClass: "dish-amount"}, [a("span", {staticClass: "amount-name"}, [t._v("菜品小计：")]), a("span", {staticClass: "amount-price"}, [t._v("￥" + t._s(100 * (t.diaForm.amount - 6 - t.diaForm.packAmount).toFixed(2) / 100))])]), a("div", {staticClass: "send-amount"}, [a("span", {staticClass: "amount-name"}, [t._v("派送费：")]), a("span", {staticClass: "amount-price"}, [t._v("￥" + t._s(6))])]), a("div", {staticClass: "package-amount"}, [a("span", {staticClass: "amount-name"}, [t._v("打包费：")]), a("span", {staticClass: "amount-price"}, [t._v("￥" + t._s(t.diaForm.packAmount ? 100 * t.diaForm.packAmount.toFixed(2) / 100 : ""))])]), a("div", {staticClass: "all-amount"}, [a("span", {staticClass: "amount-name"}, [t._v("合计：")]), a("span", {staticClass: "amount-price"}, [t._v("￥" + t._s(t.diaForm.amount ? 100 * t.diaForm.amount.toFixed(2) / 100 : ""))])]), a("div", {staticClass: "pay-type"}, [a("span", {staticClass: "pay-name"}, [t._v("支付渠道：")]), a("span", {staticClass: "pay-value"}, [t._v(t._s(1 === t.diaForm.payMethod ? "微信支付" : "支付宝支付"))])]), a("div", {staticClass: "pay-time"}, [a("span", {staticClass: "pay-name"}, [t._v("支付时间：")]), a("span", {staticClass: "pay-value"}, [t._v(t._s(t.diaForm.checkoutTime))])])])])])]), 6 !== t.dialogOrderStatus ? a("span", {
        staticClass: "dialog-footer",
        attrs: {slot: "footer"},
        slot: "footer"
      }, [2 === t.dialogOrderStatus && 2 === t.status ? a("el-checkbox", {
        model: {
          value: t.isAutoNext,
          callback: function (e) {
            t.isAutoNext = e
          },
          expression: "isAutoNext"
        }
      }, [t._v("处理完自动跳转下一条")]) : t._e(), 2 === t.dialogOrderStatus ? a("el-button", {
        on: {
          click: function (e) {
            t.orderReject(t.row, e), t.isTableOperateBtn = !1
          }
        }
      }, [t._v("拒 单")]) : t._e(), 2 === t.dialogOrderStatus ? a("el-button", {
        attrs: {type: "primary"},
        on: {
          click: function (e) {
            t.orderAccept(t.row, e), t.isTableOperateBtn = !1
          }
        }
      }, [t._v("接 单")]) : t._e(), [1, 3, 4, 5].includes(t.dialogOrderStatus) ? a("el-button", {
        on: {
          click: function (e) {
            t.dialogVisible = !1
          }
        }
      }, [t._v("返 回")]) : t._e(), 3 === t.dialogOrderStatus ? a("el-button", {
        attrs: {type: "primary"},
        on: {
          click: function (e) {
            return t.cancelOrDeliveryOrComplete(3, t.row.id, e)
          }
        }
      }, [t._v("派 送")]) : t._e(), 4 === t.dialogOrderStatus ? a("el-button", {
        attrs: {type: "primary"},
        on: {
          click: function (e) {
            return t.cancelOrDeliveryOrComplete(4, t.row.id, e)
          }
        }
      }, [t._v("完 成")]) : t._e(), [1].includes(t.dialogOrderStatus) ? a("el-button", {
        attrs: {type: "primary"},
        on: {
          click: function (e) {
            return t.cancelOrder(t.row, e)
          }
        }
      }, [t._v("取消订单")]) : t._e()], 1) : t._e()], 1), a("el-dialog", {
        staticClass: "cancelDialog",
        attrs: {
          title: t.cancelDialogTitle + "原因",
          visible: t.cancelDialogVisible,
          width: "42%",
          "before-close": function () {
            return t.cancelDialogVisible = !1, t.cancelReason = ""
          }
        },
        on: {
          "update:visible": function (e) {
            t.cancelDialogVisible = e
          }
        }
      }, [a("el-form", {attrs: {"label-width": "90px"}}, [a("el-form-item", {attrs: {label: t.cancelDialogTitle + "原因："}}, [a("el-select", {
        attrs: {placeholder: "请选择" + t.cancelDialogTitle + "原因"},
        model: {
          value: t.cancelReason, callback: function (e) {
            t.cancelReason = e
          }, expression: "cancelReason"
        }
      }, t._l("取消" === t.cancelDialogTitle ? t.cancelrReasonList : t.cancelOrderReasonList, (function (t, e) {
        return a("el-option", {key: e, attrs: {label: t.label, value: t.label}})
      })), 1)], 1), "自定义原因" === t.cancelReason ? a("el-form-item", {attrs: {label: "原因："}}, [a("el-input", {
        attrs: {
          type: "textarea",
          placeholder: "请填写您" + t.cancelDialogTitle + "的原因（限20字内）",
          maxlength: "20"
        }, model: {
          value: t.remark, callback: function (e) {
            t.remark = "string" === typeof e ? e.trim() : e
          }, expression: "remark"
        }
      })], 1) : t._e()], 1), a("span", {
        staticClass: "dialog-footer",
        attrs: {slot: "footer"},
        slot: "footer"
      }, [a("el-button", {
        on: {
          click: function (e) {
            t.cancelDialogVisible = !1, t.cancelReason = ""
          }
        }
      }, [t._v("取 消")]), a("el-button", {
        attrs: {type: "primary"},
        on: {click: t.confirmCancel}
      }, [t._v("确 定")])], 1)], 1)], 1)
    }, R = [], E = a("bd86"), B = a("fab1"), L = function (t) {
      function e() {
        var t;
        return Object(i["a"])(this, e), t = Object(c["a"])(this, Object(l["a"])(e).apply(this, arguments)), t.orderId = "", t.dialogOrderStatus = 0, t.activeIndex = 0, t.dialogVisible = !1, t.cancelDialogVisible = !1, t.cancelDialogTitle = "", t.cancelReason = "", t.remark = "", t.diaForm = [], t.row = {}, t.isAutoNext = !0, t.isSearch = !1, t.counts = 0, t.page = 1, t.pageSize = 10, t.status = 2, t.orderData = [], t.isTableOperateBtn = !0, t.cancelOrderReasonList = [{
          value: 1,
          label: "订单量较多，暂时无法接单"
        }, {value: 2, label: "菜品已销售完，暂时无法接单"}, {value: 3, label: "餐厅已打烊，暂时无法接单"}, {
          value: 0,
          label: "自定义原因"
        }], t.cancelrReasonList = [{value: 1, label: "订单量较多，暂时无法接单"}, {
          value: 2,
          label: "菜品已销售完，暂时无法接单"
        }, {value: 3, label: "骑手不足无法配送"}, {value: 4, label: "客户电话取消"}, {
          value: 0,
          label: "自定义原因"
        }], t.orderList = [{label: "全部订单", value: 0}, {label: "待付款", value: 1}, {
          label: "待接单",
          value: 2
        }, {label: "待派送", value: 3}, {label: "派送中", value: 4}, {label: "已完成", value: 5}, {
          label: "已取消",
          value: 6
        }], t
      }

      return Object(u["a"])(e, t), Object(o["a"])(e, [{
        key: "created", value: function () {
          this.getOrderListData(this.status)
        }
      }, {
        key: "getOrderListData", value: function () {
          var t = Object(n["a"])(regeneratorRuntime.mark((function t(e) {
            var a, r, s;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  return a = {page: this.page, pageSize: this.pageSize, status: e}, t.next = 3, Object(b["c"])(a);
                case 3:
                  if (r = t.sent, this.orderData = r.data.data.records, this.counts = r.data.data.total, this.$emit("getOrderListBy3Status"), !(2 === this.dialogOrderStatus && 2 === this.status && this.isAutoNext && !this.isTableOperateBtn && r.data.records.length > 1)) {
                    t.next = 12;
                    break
                  }
                  s = r.data.records[0], this.goDetail(s.id, s.status, s, s), t.next = 13;
                  break;
                case 12:
                  return t.abrupt("return", null);
                case 13:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e(e) {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "orderAccept", value: function (t, e) {
          var a = this;
          e.stopPropagation(), this.orderId = t.id, this.dialogOrderStatus = t.status, Object(b["e"])({id: this.orderId}).then((function (t) {
            1 === t.data.code ? (a.$message.success("操作成功"), a.orderId = "", a.dialogVisible = !1, a.getOrderListData(a.status)) : a.$message.error(t.data.msg)
          })).catch((function (t) {
            a.$message.error("请求出错了：" + t.message)
          }))
        }
      }, {
        key: "cancelOrder", value: function (t, e) {
          e.stopPropagation(), this.cancelDialogVisible = !0, this.orderId = t.id, this.dialogOrderStatus = t.status, this.cancelDialogTitle = "取消", this.dialogVisible = !1, this.cancelReason = ""
        }
      }, {
        key: "orderReject", value: function (t, e) {
          e.stopPropagation(), this.cancelDialogVisible = !0, this.orderId = t.id, this.dialogOrderStatus = t.status, this.cancelDialogTitle = "拒绝", this.dialogVisible = !1, this.cancelReason = ""
        }
      }, {
        key: "confirmCancel", value: function (t) {
          var e = this;
          return this.cancelReason ? "自定义原因" !== this.cancelReason || this.remark ? void ("取消" === this.cancelDialogTitle ? b["f"] : b["g"])(Object(E["a"])({id: this.orderId}, "取消" === this.cancelDialogTitle ? "cancelReason" : "rejectionReason", "自定义原因" === this.cancelReason ? this.remark : this.cancelReason)).then((function (t) {
            1 === t.data.code ? (e.$message.success("操作成功"), e.cancelDialogVisible = !1, e.orderId = "", e.getOrderListData(e.status)) : e.$message.error(t.data.msg)
          })).catch((function (t) {
            e.$message.error("请求出错了：" + t.message)
          })) : this.$message.error("请输入".concat(this.cancelDialogTitle, "原因")) : this.$message.error("请选择".concat(this.cancelDialogTitle, "原因"))
        }
      }, {
        key: "cancelOrDeliveryOrComplete", value: function (t, e, a) {
          var r = this;
          a.stopPropagation();
          var s = {status: t, id: e};
          (3 === t ? b["b"] : b["a"])(s).then((function (t) {
            1 === t.data.code ? (r.$message.success("操作成功"), r.orderId = "", r.dialogVisible = !1, r.getOrderListData(r.status)) : r.$message.error(t.data.msg)
          })).catch((function (t) {
            r.$message.error("请求出错了：" + t.message)
          }))
        }
      }, {
        key: "goDetail", value: function () {
          var t = Object(n["a"])(regeneratorRuntime.mark((function t(e, a, r, s) {
            var n, i;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  return s.stopPropagation(), this.diaForm = [], this.dialogVisible = !0, this.dialogOrderStatus = a, t.next = 6, Object(b["h"])({orderId: e});
                case 6:
                  n = t.sent, i = n.data, this.diaForm = i.data, this.row = r;
                case 10:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e(e, a, r, s) {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "handleClose", value: function () {
          this.dialogVisible = !1
        }
      }, {
        key: "handleClass", value: function (t) {
          this.activeIndex = t, 0 === t ? (this.status = 2, this.getOrderListData(2)) : (this.status = 3, this.getOrderListData(3))
        }
      }, {
        key: "handleTable", value: function (t, e, a) {
          a.stopPropagation(), this.goDetail(t.id, t.status, t, a)
        }
      }, {
        key: "handleSizeChange", value: function (t) {
          this.pageSize = t, this.getOrderListData(this.status)
        }
      }, {
        key: "handleCurrentChange", value: function (t) {
          this.page = t, this.getOrderListData(this.status)
        }
      }, {
        key: "tabList", get: function () {
          return [{label: "待接单", value: 2, num: this.orderStatics.toBeConfirmed}, {
            label: "待派送",
            value: 3,
            num: this.orderStatics.confirmed
          }]
        }
      }]), e
    }(p["c"]);
    Object(d["a"])([Object(p["b"])({default: ""})], L.prototype, "orderStatics", void 0), L = Object(d["a"])([Object(p["a"])({
      name: "Orderview",
      components: {Empty: B["a"]}
    })], L);
    var P = L, I = P, V = (a("4455"), a("ead7"), Object(y["a"])(I, A, R, !1, null, "7afbbb1e", null)), N = V.exports,
      z = function (t) {
        function e() {
          return Object(i["a"])(this, e), Object(c["a"])(this, Object(l["a"])(e).apply(this, arguments))
        }

        return Object(u["a"])(e, t), e
      }(p["c"]);
    Object(d["a"])([Object(p["b"])()], z.prototype, "dishesData", void 0), z = Object(d["a"])([Object(p["a"])({
      components: {orderList: N},
      name: "cuisineview"
    })], z);
    var M = z, q = M, U = Object(y["a"])(q, $, T, !1, null, null, null), J = U.exports, W = function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("div", {staticClass: "container"}, [a("h2", {staticClass: "homeTitle"}, [t._v("\n    套餐总览"), a("span", [a("router-link", {attrs: {to: "setmeal"}}, [t._v("套餐管理")])], 1)]), a("div", {staticClass: "orderviewBox"}, [a("ul", [a("li", [t._m(0), a("span", {staticClass: "num"}, [t._v(t._s(t.setMealData.sold))])]), a("li", [t._m(1), a("span", {staticClass: "num"}, [t._v(t._s(t.setMealData.discontinued))])]), a("li", {staticClass: "add"}, [a("router-link", {attrs: {to: "setmeal/add"}}, [a("i"), a("p", [t._v("新增套餐")])])], 1)])])])
    }, X = [function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-open"}), t._v("已启售")])
    }, function () {
      var t = this, e = t.$createElement, a = t._self._c || e;
      return a("span", {staticClass: "status"}, [a("i", {staticClass: "iconfont icon-stop"}), t._v("已停售")])
    }], H = function (t) {
      function e() {
        return Object(i["a"])(this, e), Object(c["a"])(this, Object(l["a"])(e).apply(this, arguments))
      }

      return Object(u["a"])(e, t), e
    }(p["c"]);
    Object(d["a"])([Object(p["b"])()], H.prototype, "setMealData", void 0), H = Object(d["a"])([Object(p["a"])({name: "SetMeal"})], H);
    var G = H, Q = G, K = Object(y["a"])(Q, W, X, !1, null, null, null), Z = K.exports, tt = function (t) {
      function e() {
        var t;
        return Object(i["a"])(this, e), t = Object(c["a"])(this, Object(l["a"])(e).apply(this, arguments)), t.todayData = {}, t.overviewData = {}, t.orderviewData = {}, t.flag = 2, t.tateData = [], t.dishesData = {}, t.setMealData = {}, t.orderListData = [], t.counts = 0, t.page = 1, t.pageSize = 10, t.status = 2, t.orderStatics = {}, t
      }

      return Object(u["a"])(e, t), Object(o["a"])(e, [{
        key: "created", value: function () {
          this.init()
        }
      }, {
        key: "init", value: function () {
          var t = this;
          this.$nextTick((function () {
            t.getBusinessData(), t.getOrderStatisticsData(), t.getOverStatisticsData(), t.getSetMealStatisticsData()
          }))
        }
      }, {
        key: "getBusinessData", value: function () {
          var t = Object(n["a"])(regeneratorRuntime.mark((function t() {
            var e;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  return t.next = 2, Object(v["b"])();
                case 2:
                  e = t.sent, this.overviewData = e.data.data;
                case 4:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e() {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "getOrderStatisticsData", value: function () {
          var t = Object(n["a"])(regeneratorRuntime.mark((function t() {
            var e;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  return t.next = 2, Object(v["c"])();
                case 2:
                  e = t.sent, this.orderviewData = e.data.data;
                case 4:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e() {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "getOverStatisticsData", value: function () {
          var t = Object(n["a"])(regeneratorRuntime.mark((function t() {
            var e;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  return t.next = 2, Object(v["e"])();
                case 2:
                  e = t.sent, this.dishesData = e.data.data;
                case 4:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e() {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "getSetMealStatisticsData", value: function () {
          var t = Object(n["a"])(regeneratorRuntime.mark((function t() {
            var e;
            return regeneratorRuntime.wrap((function (t) {
              while (1) switch (t.prev = t.next) {
                case 0:
                  return t.next = 2, Object(v["f"])();
                case 2:
                  e = t.sent, this.setMealData = e.data.data;
                case 4:
                case"end":
                  return t.stop()
              }
            }), t, this)
          })));

          function e() {
            return t.apply(this, arguments)
          }

          return e
        }()
      }, {
        key: "getOrderListBy3Status", value: function () {
          var t = this;
          Object(b["d"])({}).then((function (e) {
            1 === e.data.code ? t.orderStatics = e.data.data : t.$message.error(e.data.msg)
          })).catch((function (e) {
            t.$message.error("请求出错了：" + e.message)
          }))
        }
      }]), e
    }(p["c"]);
    tt = Object(d["a"])([Object(p["a"])({
      name: "Dashboard",
      components: {Overview: C, Orderview: Y, CuisineStatistics: J, SetMealStatistics: Z, OrderList: N}
    })], tt);
    var et = tt, at = et, rt = Object(y["a"])(at, r, s, !1, null, null, null);
    e["default"] = rt.exports
  }, "949e": function (t, e, a) {
  }, 9744: function (t, e, a) {
    "use strict";
    var r = a("4588"), s = a("be13");
    t.exports = function (t) {
      var e = String(s(this)), a = "", n = r(t);
      if (n < 0 || n == 1 / 0) throw RangeError("Count can't be negative");
      for (; n > 0; (n >>>= 1) && (e += e)) 1 & n && (a += e);
      return a
    }
  }, aea7: function (t, e, a) {
    "use strict";
    var r = a("949e"), s = a.n(r);
    s.a
  }, be14: function (t, e, a) {
    "use strict";
    var r = a("8830"), s = a.n(r);
    s.a
  }, c0d1: function (t, e, a) {
  }, cdc4: function (t, e, a) {
    t.exports = {menuBg: "#343744", menuText: "#bfcbd9", menuActiveText: "#ffc200"}
  }, d153: function (t, e, a) {
  }, d4eb: function (t, e) {
    t.exports = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAqCAYAAADBNhlmAAABcElEQVRYR+3YwUrDQBAG4JlS8e7BU1QqyaUN4iP4LCoo3n0J79aD4tW7D1DyBkUhCBqIggcfwYN0JGraWjbdTXbZGUpyTXbz5d/sZrIIwg8U7oMWaDtCbYKiEgzDvSAINj6SJPmyhZXtnQwxEWEvHAwB4AQBnne2NmNXSGvgDy4a3ADB4d9TUxe721n2+O4iRSugAgeAePWapWcucEUfjYFqHNzmL+kxIhIr0BeuUYI+cbWBvnG1gBw4YyAXzgjIidMCuXFLgRJwlcBfXH8EhAdzC+64g517VwvwrJ/JJ62t3+VP4zdV38ovSS/qX9AEzt1j1D0i4kOepfurAxQ/xEXUBXI3iq+J6GgaPbovBnSv0dJqRgJSW25xI7VA7uE2ApbIhdK+KHedF6iL76QxkAtZC8iBrA30jWwE9IlsDKxGCvntLGeb6B/3/8j4EohOxW19zK9bYjePdB98m/NWk8TmxqZtW6BpUlXXtQnaJvgN7kUqOhRI5j0AAAAASUVORK5CYII="
  }, d666: function (t, e, a) {
    "use strict";
    var r = function () {
        var t = this, e = t.$createElement, r = t._self._c || e;
        return r("div", {staticClass: "HeadLable"}, [t.goback ? r("span", {
          staticClass: "goBack",
          on: {
            click: function (e) {
              return t.goBack()
            }
          }
        }, [r("img", {
          attrs: {
            src: a("d4eb"),
            alt: ""
          }
        }), t._v(" 返回")]) : t._e(), t.butList ? t._e() : r("span", [t._v(t._s(t.title))]), t.butList ? r("div", [t._t("default")], 2) : t._e()])
      }, s = [], n = a("d225"), i = a("b0b4"), o = a("308d"), c = a("6bb5"), l = a("4e2b"), u = a("9ab4"), d = a("60a3"),
      p = function (t) {
        function e() {
          return Object(n["a"])(this, e), Object(o["a"])(this, Object(c["a"])(e).apply(this, arguments))
        }

        return Object(l["a"])(e, t), Object(i["a"])(e, [{
          key: "toggleClick", value: function () {
            this.$emit("toggleClick")
          }
        }, {
          key: "goBack", value: function () {
            this.$router.go(-1)
          }
        }]), e
      }(d["c"]);
    Object(u["a"])([Object(d["b"])({default: !1})], p.prototype, "goback", void 0), Object(u["a"])([Object(d["b"])({default: !1})], p.prototype, "butList", void 0), Object(u["a"])([Object(d["b"])({default: "集团管理"})], p.prototype, "title", void 0), p = Object(u["a"])([Object(d["a"])({name: "Hamburger"})], p);
    var v = p, b = v, m = (a("aea7"), a("2877")), f = Object(m["a"])(b, r, s, !1, null, "6793a8ec", null);
    e["a"] = f.exports
  }, d9cf: function (t, e, a) {
    "use strict";
    a.d(e, "a", (function () {
      return s
    })), a.d(e, "b", (function () {
      return n
    })), a.d(e, "d", (function () {
      return i
    })), a.d(e, "c", (function () {
      return o
    })), a.d(e, "f", (function () {
      return c
    })), a.d(e, "e", (function () {
      return l
    }));
    a("f576"), a("a481"), a("3b2b"), a("6b54");

    function r(t, e) {
      var a, r = new Date(e),
        s = {"Y+": r.getFullYear().toString(), "m+": (r.getMonth() + 1).toString(), "d+": r.getDate().toString()};
      for (var n in s) a = new RegExp("(" + n + ")").exec(t), a && (t = t.replace(a[1], 1 == a[1].length ? s[n] : s[n].padStart(a[1].length, "0")));
      return t
    }

    var s = function () {
      var t = new Date((new Date).toLocaleDateString()).getTime(), e = t - 864e5, a = e + 864e5 - 1,
        s = r("YYYY-mm-dd", e), n = r("YYYY-mm-dd", a);
      return [s, n]
    }, n = function () {
      var t = new Date((new Date).toLocaleDateString()).getTime(), e = t - 864e5, a = r("YYYY.mm.dd", e),
        s = r("YYYY.mm.dd", t);
      return [a, s]
    }, i = function () {
      var t = new Date((new Date).toLocaleDateString()).getTime(), e = t - 6048e5, a = t - 1, s = r("YYYY-mm-dd", e),
        n = r("YYYY-mm-dd", a);
      return [s, n]
    }, o = function () {
      var t = new Date((new Date).toLocaleDateString()).getTime(), e = t - 2592e6, a = t - 1, s = r("YYYY-mm-dd", e),
        n = r("YYYY-mm-dd", a);
      return [s, n]
    }, c = function () {
      var t = new Date((new Date).toLocaleDateString()).getTime(), e = (new Date).getDay(),
        a = t - 24 * (e - 1) * 60 * 60 * 1e3, s = t + 24 * (7 - e) * 60 * 60 * 1e3, n = r("YYYY-mm-dd", a),
        i = r("YYYY-mm-dd", s);
      return [n, i]
    }, l = function () {
      var t = (new Date).getFullYear(), e = (new Date).getMonth(), a = new Date(t, e, 1).getTime(),
        s = new Date(t, e + 1, 0).getTime() + 864e5 - 1, n = r("YYYY-mm-dd", a), i = r("YYYY-mm-dd", s);
      return [n, i]
    }
  }, ead7: function (t, e, a) {
    "use strict";
    var r = a("cdc4"), s = a.n(r);
    s.a
  }, f576: function (t, e, a) {
    "use strict";
    var r = a("5ca1"), s = a("2e08"), n = a("a25f"), i = /Version\/10\.\d+(\.\d+)?( Mobile\/\w+)? Safari\//.test(n);
    r(r.P + r.F * i, "String", {
      padStart: function (t) {
        return s(this, t, arguments.length > 1 ? arguments[1] : void 0, !0)
      }
    })
  }, fab1: function (t, e, a) {
    "use strict";
    var r = function () {
        var t = this, e = t.$createElement, r = t._self._c || e;
        return r("div", {staticClass: "empty-box"}, [r("div", {staticClass: "img-box"}, [t.isSearch ? r("img", {attrs: {src: a("2235")}}) : r("img", {
          attrs: {
            src: a("38b8f"),
            alt: ""
          }
        }), r("p", [t._v(t._s(t.isSearch ? "Sorry，木有找到您搜索的内容哦~" : "这里空空如也~"))])])])
      }, s = [], n = a("d225"), i = a("308d"), o = a("6bb5"), c = a("4e2b"), l = a("9ab4"), u = a("60a3"),
      d = function (t) {
        function e() {
          return Object(n["a"])(this, e), Object(i["a"])(this, Object(o["a"])(e).apply(this, arguments))
        }

        return Object(c["a"])(e, t), e
      }(u["c"]);
    Object(l["a"])([Object(u["b"])({default: !1})], d.prototype, "isSearch", void 0), d = Object(l["a"])([Object(u["a"])({name: "Empty"})], d);
    var p = d, v = p, b = (a("be14"), a("2877")), m = Object(b["a"])(v, r, s, !1, null, "399b6bbf", null);
    e["a"] = m.exports
  }
}]);
//# sourceMappingURL=dashboard.630a609e.js.map
