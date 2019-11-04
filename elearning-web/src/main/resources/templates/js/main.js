;(function ($) {

    'use strict'
    // 判断是否移动设备浏览
    var isMobile = {
        Android: function () {
            return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function () {
            return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function () {
            return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function () {
            return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function () {
            return navigator.userAgent.match(/IEMobile/i);
        },
        any: function () {
            return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
    };
    // 幻灯片插件owl.carousel.js的调用
    // 首页热门课程的滚动
    var courseCarousel = function () {
        $('.flat-row').each(function () {
            if ($().owlCarousel) {
                $(this).find('.flat-course-grid').owlCarousel({
                    loop: true,
                    margin: 30,
                    nav: true,
                    dots: false,
                    autoplay: false,
                    responsive: {
                        0: {
                            items: 1
                        },

                        479: {
                            items: 2
                        },
                        991: {
                            items: 2
                        },
                        1200: {
                            items: 3
                        }
                    }
                });
            }
        });
    };
    // 导航栏在不同设备下显示不同样式
    // responsive.css响应式布局
    var responsiveMenu = function () {
        var menuType = 'desktop';

        $(window).on('load resize', function () {
            var currMenuType = 'desktop';
            if (matchMedia('only screen and (max-width: 1199px)').matches) {
                currMenuType = 'mobile';
            }

            if (currMenuType !== menuType) {
                menuType = currMenuType;

                if (currMenuType === 'mobile') {
                    var $mobileMenu = $('#mainNav').attr('id', 'mainNavMobi').hide();
                    var hasChildMenu = $('#mainNavMobi').find('li:has(ul)');

                    $('#header').after($mobileMenu);
                    hasChildMenu.children('ul').hide();
                    hasChildMenu.children('a').after('<span class="btn-submenu"></span>');
                    $('.btn-menu').removeClass('active');
                } else {
                    var $desktopMenu = $('#mainNavMobi').attr('id', 'mainNav').removeAttr('style');

                    $desktopMenu.find('.submenu').removeAttr('style');
                    $('#header').find('.nav-wrap').append($desktopMenu);
                    $('.btn-submenu').remove();
                }
            }
        });

        $('.btn-menu').on('click', function () {
            $('#mainNavMobi').slideToggle(300);
            $(this).toggleClass('active');
        });

        $(document).on('click', '#mainNavMobi li .btn-submenu', function (e) {
            $(this).toggleClass('active').next('ul').slideToggle(300);
            e.stopImmediatePropagation()
        });
    }
    // 幻灯片插件owl.carousel.js的调用
    // 首页合作伙伴的滚动
    var partnersCarousel = function () {
        $('.flat-row').each(function () {
            if ($().owlCarousel) {
                $(this).find('.partners-slider').owlCarousel({
                    loop: true,
                    margin: 30,
                    nav: false,
                    dots: false,
                    autoplay: true,
                    responsive: {
                        0: {
                            items: 2
                        },
                        479: {
                            items: 3
                        },
                        767: {
                            items: 4
                        },
                        991: {
                            items: 5
                        },
                        1200: {
                            items: 6
                        }
                    }
                });
            }
        });
    };

    // 滚动监听插件jquery-waypoint.js
    // 首页滚动到倒计时统计学员数量时，开始加载数据上升动画
    var detectViewport = function () {
        if ($('#waypoint').length > 0) {
            $('[data-waypoint-active="yes"]').waypoint(function () {
                $(this).trigger('on-appear');
            }, {offset: '90%', triggerOnce: true});

            $(window).on('load', function () {
                setTimeout(function () {
                    $.waypoints('refresh');
                }, 100);
            });
        }
    };

    // 首页的倒计时统计学员数量上升动画
    var counter = function () {
        $('.counter-content').on('on-appear', function () {
            $(this).find('.numb-count').each(function () {
                var to = parseInt(($(this).attr('data-to')), 10), speed = parseInt(($(this).attr('data-speed')), 10);
                if ($().countTo) {
                    $(this).countTo({
                        to: to,
                        speed: speed
                    });
                }
            });
        });
    };
    // 讲师界面的字母索引
    var portfolioIsotope = function () {
        if ($().isotope) {
            var $container = $('.flat-teacher-team-isotope');
            $container.imagesLoaded(function () {
                $container.isotope({
                    itemSelector: '.flat-teacher',
                    transitionDuration: '1s'
                });
            });

            $('.portfolio-filter li').on('click', function () {
                // 点击页码时异步加载数据
                $.ajax("/team/page/",
                    {
                        method: "get",
                        success: function (data) {
                            $("#teacherListWrapper").html(data);
                            var selector = $(this).find("a").attr('data-filter');
                            $('.portfolio-filter li').removeClass('active');
                            $(this).addClass('active');
                            $container.isotope({filter: selector});
                        }
                    }
                );
                return false;
            });
        }
        ;
    };

// 返回顶部
    var goTop = function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 800) {
                $('.go-top').addClass('show');
            } else {
                $('.go-top').removeClass('show');
            }
        });

        $('.go-top').on('click', function () {
            $("html, body").animate({scrollTop: 0}, 1000, 'easeInOutExpo');
            return false;
        });
    };


// 课程列表页的列表显示方式
    var selectLayout = function () {
        $(".list-grid a").on("click", function () {
            $(this).parent().find("a").removeClass("active");
            $(this).addClass("active");
            $(".main-content").removeClass("course-list course-grid");
            $(".main-content").addClass($(this).data("layout"));
        })
    }

// 点击头部的搜索icon显示搜索的悬浮框
    var flatSearch = function () {
        $(document).on('click', function (e) {
            var clickID = e.target.id;
            if ((clickID != 's')) {
                $('.top-search').removeClass('show');
            }
        });

        $('.show-search').on('click', function (event) {
            event.stopPropagation();
        });

        $('.search-form').on('click', function (event) {
            event.stopPropagation();
        });

        $('.show-search').on('click', function () {
            if (!$('.top-search').hasClass("show"))
                $('.top-search').addClass('show');
            else
                $('.top-search').removeClass('show');
        });
    }

    $(function () {
        responsiveMenu();
        courseCarousel();
        partnersCarousel();
        portfolioIsotope();
        counter();
        detectViewport();
        goTop();
        selectLayout();
        flatSearch();
    });

})(jQuery);