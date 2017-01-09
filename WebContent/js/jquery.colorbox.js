(function(C) {
    var s = "colorbox",
        q = "hover",
        y = true,
        Q = false,
        W, o = !C.support.opacity,
        S = o && !window.XMLHttpRequest,
        z = "cbox_open",
        K = "cbox_load",
        v = "cbox_complete",
        J = "cbox_cleanup",
        p = "cbox_closed",
        T = "cbox_vkresize",
        N = "resize.cbox_resize",
        H, U, V, d, A, l, b, G, c, O, E, h, g, t, k, n, L, m, I, u, X, j, f, a, r, i, B, P, x, M, D = {
            transition: "elastic",
            speed: 450,
            width: Q,
            height: Q,
            innerWidth: Q,
            innerHeight: Q,
            initialWidth: "400",
            initialHeight: "400",
            maxWidth: Q,
            maxHeight: Q,
            scalePhotos: y,
            scrolling: y,
            inline: Q,
            html: Q,
            iframe: Q,
            photo: Q,
            href: Q,
            title: Q,
            rel: Q,
            opacity: 0.8,
            preloading: y,
            current: "image {current} of {total}",
            previous: "previous",
            next: "next",
            close: "close",
            open: Q,
            overlayClose: y,
            tempValue: Q,
            slideshow: Q,
            slideshowAuto: y,
            slideshowSpeed: 2500,
            slideshowStart: "start slideshow",
            slideshowStop: "stop slideshow",
            onOpen: Q,
            onLoad: Q,
            onComplete: Q,
            onCleanup: Q,
            onVkresize: Q,
            onClosed: Q,
            rePostionColorBoxOnResize: y
        };

    function F(Y, Z) {
        Z = Z === "x" ? O.width() : O.height();
        return (typeof Y === "string") ? Math.round((Y.match(/%/) ? (Z / 100) * parseInt(Y, 10) : parseInt(Y, 10))) : Y;
    }

    function w(Y) {
        Y = C.isFunction(Y) ? Y.call(r) : Y;
        return P.photo || Y.match(/\.(gif|png|jpg|jpeg|bmp)(?:\?([^#]*))?(?:#(\.*))?$/i);
    }

    function R() {
        for (var Y in P) {
            if (C.isFunction(P[Y]) && Y.substring(0, 2) !== "on") {
                P[Y] = P[Y].call(r);
            }
        }
        P.rel = P.rel || r.rel;
        P.href = P.href || r.href;
        P.title = P.title || r.title;
    }

    function e(Y) {
        r = Y;
        P = C(r).data(s);
        R();
        if (P.rel && P.rel !== "nofollow") {
            c = C(".cboxElement").filter(function() {
                var Z = C(this).data(s).rel || this.rel;
                return (Z === P.rel);
            });
            B = c.index(r);
            if (B < 0) {
                c = c.add(r);
                B = c.length - 1;
            }
        } else {
            c = C(r);
            B = 0;
        }
        if (!x) {
            x = y;
            M = y;
            i = r;
            i.blur();
            C(document).bind("keydown.cbox_close", function(Z) {
                if (Z.keyCode === 27) {
                    Z.preventDefault();
                    W.close();
                }
            }).bind("keydown.cbox_arrows", function(Z) {
                if (c.length > 1) {
                    if (Z.keyCode === 37) {
                        Z.preventDefault();
                        I.click();
                    } else {
                        if (Z.keyCode === 39) {
                            Z.preventDefault();
                            m.click();
                        }
                    }
                }
            });
            if (P.overlayClose) {
                H.css({
                    "cursor": "pointer"
                }).one("click", W.close);
            }
            C.event.trigger(z);
            if (P.onOpen) {
                P.onOpen.call(r);
            }
            H.css({
                "opacity": P.opacity
            }).show();
            P.w = F(P.initialWidth, "x");
            P.h = F(P.initialHeight, "y");
            W.position(0);
            if (S) {
                O.bind("resize.cboxie6 scroll.cboxie6", function() {
                    H.css({
                        width: O.width(),
                        height: O.height(),
                        top: O.scrollTop(),
                        left: O.scrollLeft()
                    });
                }).trigger("scroll.cboxie6");
            }
        }
        n.add(I).add(m).add(L).add(k).hide();
        u.html(P.close).show();
        W.slideshow();
        W.load();
    }
    W = C.fn.colorbox = function(Y, aa) {
        var Z = this;
        if (!Z.length) {
            if (Z.selector === "") {
                Z = C("<a/>");
                Y.open = y;
            } else {
                return this;
            }
        }
        Z.each(function() {
            var ab = C.extend({}, C(this).data(s) ? C(this).data(s) : D, Y);
            C(this).data(s, ab).addClass("cboxElement");
            if (aa) {
                C(this).data(s).onComplete = aa;
            }
        });
        if (Y && Y.open) {
            e(Z);
        }
        return this;
    };
    W.imgResize = function(Y, Z) {
        if (!x) {
            return;
        }
        var aa = P.transition === "none" ? 0 : P.speed;
        O.unbind(N);
        P.w = Y;
        P.h = Z;
        E.css({
            width: P.w,
            height: P.h
        });
        W.position(aa);
    };
    W.init = function() {
        function Y(Z) {
            return C('<div id="cbox' + Z + '"/>');
        }
        O = C(window);
        U = C('<div id="colorbox"/>');
        H = Y("Overlay").hide();
        V = Y("Wrapper");
        d = Y("Content").append(E = Y("LoadedContent").css({
            width: 0,
            height: 0
        }), g = Y("LoadingOverlay"), t = Y("LoadingGraphic"), k = Y("Title"), n = Y("Current"), L = Y("Slideshow"), m = Y("Next"), I = Y("Previous"), u = Y("Close"));
        V.append(C("<div/>").append(Y("TopLeft"), A = Y("TopCenter"), Y("TopRight")), C("<div/>").append(l = Y("MiddleLeft"), d, b = Y("MiddleRight")), C("<div/>").append(Y("BottomLeft"), G = Y("BottomCenter"), Y("BottomRight"))).children().children().css({
            "float": "left"
        });
        h = C("<div style='position:absolute; top:0; left:0; width:9999px; height:0;'/>");
        C("body").prepend(H, U.append(V, h));
        if (o) {
            U.addClass("cboxIE");
            if (S) {
                H.css("position", "absolute");
            }
        }
        d.children().bind("mouseover mouseout", function() {
            C(this).toggleClass(q);
        }).addClass(q);
        X = A.height() + G.height() + d.outerHeight(y) - d.height();
        j = l.width() + b.width() + d.outerWidth(y) - d.width();
        f = E.outerHeight(y);
        a = E.outerWidth(y);
        U.css({
            "padding-bottom": X,
            "padding-right": j
        }).hide();
        m.click(W.next);
        I.click(W.prev);
        u.click(W.close);
        d.children().removeClass(q);
        C(".cboxElement").on("click", function(Z) {
            if (Z.button !== 0 && typeof Z.button !== "undefined") {
                return y;
            } else {
                e(this);
                return Q;
            }
        });
    };
    W.position = function(ad, aa) {
        var ac, Z = O.height(),
            ab = Math.max(Z - P.h - f - X, 0) / 2 + O.scrollTop(),
            Y = Math.max(document.documentElement.clientWidth - P.w - a - j, 0) / 2 + O.scrollLeft();
        ac = (U.width() === P.w + a && U.height() === P.h + f) ? 0 : ad;
        V[0].style.width = V[0].style.height = "9999px";

        function ae(af) {
            A[0].style.width = G[0].style.width = d[0].style.width = af.style.width;
            t[0].style.height = g[0].style.height = d[0].style.height = l[0].style.height = b[0].style.height = af.style.height;
        }
        if (P.rePostionColorBoxOnResize === false) {
            ab = 0;
        }
        U.dequeue().animate({
            width: P.w + a,
            height: P.h + f,
            top: ab,
            left: Y
        }, {
            duration: ac,
            complete: function() {
                ae(this);
                M = Q;
                V[0].style.width = (P.w + a + j) + "px";
                V[0].style.height = (P.h + f + X) + "px";
                if (aa) {
                    aa();
                }
            },
            step: function() {
                ae(this);
            }
        });
    };
    W.vkResize = function(Y, aa, Z) {
        if (!x) {
            return;
        }
        var ab = P.transition === "none" ? 0 : P.speed;
        O.unbind(N);
        P.w = Y;
        P.h = aa;
        E.css({
            width: P.w,
            height: P.h
        });
        C.event.trigger(T);
        if (P.onVkresize) {
            P.onVkresize.call(r);
        }
        if (typeof Z != "undefined" && Z === false) {
            P.rePostionColorBoxOnResize = false;
        }
        W.position(ab);
    };
    W.resize = function(ac) {
        if (!x) {
            return;
        }
        var ad, ab, Z, af, aj, Y, ah, aa = P.transition === "none" ? 0 : P.speed;
        O.unbind(N);
        if (!ac) {
            ah = setTimeout(function() {
                var ak = E.wrapInner("<div style='overflow:auto'></div>").children();
                P.h = ak.height();
                ak.replaceWith(ak.children());
                W.position(aa);
            }, 1);
            return;
        }
        E.remove();
        E = C('<div id="cboxLoadedContent"/>').html(false);

        function ag() {
            if (P.tempValue) {
                P.w = "auto";
            } else {
                P.w = P.w || E.width();
                P.w = P.mw && P.mw < P.w ? P.mw : P.w;
            }
            return P.w;
        }

        function ae() {
            if (P.tempValue) {
                P.h = "1";
            } else {
                P.h = P.h || E.height();
                P.h = P.mh && P.mh < P.h ? P.mh : P.h;
            }
            return P.h;
        }
        E.hide().appendTo(h).css({
            width: ag(),
            overflow: P.scrolling ? "auto" : "hidden"
        }).css({
            height: ae()
        }).css({
            top: 10
        }).prependTo(d);
        C("#cboxPhoto").css({
            cssFloat: "none"
        });
        if (S) {
            C("select:not(#colorbox select)").filter(function() {
                return this.style.visibility !== "hidden";
            }).css({
                "visibility": "hidden"
            }).one(J, function() {
                this.style.visibility = "inherit";
            });
        }

        function ai(ak) {
            W.position(ak, function() {
                if (!x) {
                    return;
                }
                if (o) {
                    if (Y) {
                        E.fadeIn(100);
                    }
                    U[0].style.removeAttribute("filter");
                }
                if (P.iframe) {
                    E.append("<iframe id='cboxIframe'" + (P.scrolling ? " " : "scrolling='no'") + " name='iframe_" + new Date().getTime() + "' frameborder=0 src='" + P.href + "' " + (o ? "allowtransparency='true'" : "") + " />");
                }
                E.show();
                U.show();
                k.show().html(P.title);
                if (c.length > 1) {
                    n.html(P.current.replace(/\{current\}/, B + 1).replace(/\{total\}/, c.length)).show();
                    m.html(P.next).show();
                    I.html(P.previous).show();
                    if (P.slideshow) {
                        L.show();
                    }
                }
                g.hide();
                t.hide();
                C.event.trigger(v);
                if (P.onComplete) {
                    P.onComplete.call(r);
                }
                if (P.transition === "fade") {
                    U.fadeTo(aa, 1, function() {
                        if (o) {
                            U[0].style.removeAttribute("filter");
                        }
                    });
                }
                O.bind(N, function() {
                    W.position(0);
                });
            });
        }
        if ((P.transition === "fade" && U.fadeTo(aa, 0, function() {
                ai(0);
            })) || ai(aa)) {}
        if (P.preloading && c.length > 1) {
            ab = B > 0 ? c[B - 1] : c[c.length - 1];
            af = B < c.length - 1 ? c[B + 1] : c[0];
            aj = C(af).data(s).href || af.href;
            Z = C(ab).data(s).href || ab.href;
            if (w(aj)) {
                C("<img />").attr("src", aj);
            }
            if (w(Z)) {
                C("<img />").attr("src", Z);
            }
        }
    };
    W.load = function() {
        var Z, Y, ab, aa = W.resize;
        M = y;
        r = c[B];
        P = C(r).data(s);
        R();
        C.event.trigger(K);
        if (P.onLoad) {
            P.onLoad.call(r);
        }
        P.h = P.height ? F(P.height, "y") - f - X : P.innerHeight ? F(P.innerHeight, "y") : Q;
        P.w = P.width ? F(P.width, "x") - a - j : P.innerWidth ? F(P.innerWidth, "x") : Q;
        P.mw = P.w;
        P.mh = P.h;
        if (P.maxWidth) {
            P.mw = F(P.maxWidth, "x") - a - j;
            P.mw = P.w && P.w < P.mw ? P.w : P.mw;
        }
        if (P.maxHeight) {
            P.mh = F(P.maxHeight, "y") - f - X;
            P.mh = P.h && P.h < P.mh ? P.h : P.mh;
        }
        Z = P.href;
        g.show();
        t.show();
        if (P.inline) {
            C('<div id="cboxInlineTemp" />').hide().insertBefore(C(Z)[0]).bind(K + " " + J, function() {
                C(this).replaceWith(E.children());
            });
            aa(C(Z));
        } else {
            if (P.iframe) {
                aa(P.iframe);
            } else {
                if (P.html) {
                    aa(P.html);
                } else {
                    if (w(Z)) {
                        Y = new Image();
                        Y.onload = function() {
                            var ac;
                            Y.onload = null;
                            Y.id = "cboxPhoto";
                            C(Y).css({
                                margin: "auto",
                                border: "none",
                                display: "block",
                                cssFloat: "left"
                            });
                            if (P.scalePhotos) {
                                ab = function() {
                                    Y.height -= Y.height * ac;
                                    Y.width -= Y.width * ac;
                                };
                                if (P.mw && Y.width > P.mw) {
                                    ac = (Y.width - P.mw) / Y.width;
                                    ab();
                                }
                                if (P.mh && Y.height > P.mh) {
                                    ac = (Y.height - P.mh) / Y.height;
                                    ab();
                                }
                            }
                            if (P.h) {
                                Y.style.marginTop = Math.max(P.h - Y.height, 0) / 2 + "px";
                            }
                            aa(Y);
                            if (c.length > 1) {
                                C(Y).css({
                                    cursor: "pointer"
                                }).click(W.next);
                            }
                            if (o) {
                                Y.style.msInterpolationMode = "bicubic";
                            }
                        };
                        Y.src = Z;
                    } else {
                        C("<div />").appendTo(h).load(Z, function(ac, ad) {
                            if (ad === "success") {
                                aa(this);
                            } else {
                                aa(C("<p>Request unsuccessful.</p>"));
                            }
                        });
                    }
                }
            }
        }
    };
    W.next = function() {
        if (!M) {
            B = B < c.length - 1 ? B + 1 : 0;
            W.load();
        }
    };
    W.prev = function() {
        if (!M) {
            B = B > 0 ? B - 1 : c.length - 1;
            W.load();
        }
    };
    W.slideshow = function() {
        var Z, Y, aa = "cboxSlideshow_";
        L.bind(p, function() {
            L.unbind();
            clearTimeout(Y);
            U.removeClass(aa + "off" + " " + aa + "on");
        });

        function ab() {
            L.text(P.slideshowStop).bind(v, function() {
                Y = setTimeout(W.next, P.slideshowSpeed);
            }).bind(K, function() {
                clearTimeout(Y);
            }).one("click", function() {
                Z();
                C(this).removeClass(q);
            });
            U.removeClass(aa + "off").addClass(aa + "on");
        }
        Z = function() {
            clearTimeout(Y);
            L.text(P.slideshowStart).unbind(v + " " + K).one("click", function() {
                ab();
                Y = setTimeout(W.next, P.slideshowSpeed);
                C(this).removeClass(q);
            });
            U.removeClass(aa + "on").addClass(aa + "off");
        };
        if (P.slideshow && c.length > 1) {
            if (P.slideshowAuto) {
                ab();
            } else {
                Z();
            }
        }
    };
    W.close = function() {
        C.event.trigger(J);
        if (P.onCleanup) {
            P.onCleanup.call(r);
        }
        x = Q;
        C(document).unbind("keydown.cbox_close keydown.cbox_arrows");
        O.unbind(N + " resize.cboxie6 scroll.cboxie6");
        H.css({
            cursor: "auto"
        }).fadeOut("fast");
        U.stop(y, Q).fadeOut("fast", function() {
            C("#colorbox iframe").attr("src", "about:blank");
            E.remove();
            U.css({
                "opacity": 1
            });
            try {
                i.focus();
            } catch (Y) {}
            C.event.trigger(p);
            if (P.onClosed) {
                P.onClosed.call(r);
            }
        });
    };
    W.element = function() {
        return C(r);
    };
    W.settings = D;
    C(W.init);
}(jQuery));