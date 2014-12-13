<%-- 
    Document   : sideBar
    Created on : 2-dic-2014, 20.50.49
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="./dist/components/sideBar/sideBar.css" rel="stylesheet">
<link rel='stylesheet' href='http://s.codepen.io/assets/reset/normalize.css'>
<script src='http://s.codepen.io/assets/libs/modernizr.js'></script>
<!-- Overlay for fixed sidebar -->
<div class="sidebar-overlay"></div>

<!-- Material sidebar -->
<aside id="sidebar" class="sidebar sidebar-default open" role="navigation">
    <!-- Sidebar header -->
    <div class="sidebar-header header-cover">
        <!-- Top bar -->
        <div class="top-bar"></div>
        <!-- Sidebar toggle button -->
        <button type="button" class="sidebar-toggle btn btn-default">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </button>
        <!-- Sidebar brand image -->
        <div class="sidebar-image">
            <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/53474/atom_profile_01.jpg">
        </div>
        <!-- Sidebar brand name -->
        <a data-toggle="dropdown" class="sidebar-brand" href="#settings-dropdown">
            john.doe@gmail.com
            <b class="caret"></b>
        </a>
    </div>

    <!-- Sidebar navigation -->
    <ul class="nav sidebar-nav">
        <li class="dropdown">
            <ul id="settings-dropdown" class="dropdown-menu">
                <li>
                    <a href="#" tabindex="-1">
                        Profile
                    </a>
                </li>
                <li>
                    <a href="#" tabindex="-1">
                        Settings
                    </a>
                </li>
                <li>
                    <a href="#" tabindex="-1">
                        Help
                    </a>
                </li>
                <li>
                    <a href="#" tabindex="-1">
                        Exit
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">
                <i class="sidebar-icon md-inbox"></i>
                Inbox
            </a>
        </li>
        <li>
            <a href="#">
                <i class="sidebar-icon md-star"></i>
                Starred
            </a>
        </li>
        <li>
            <a href="#">
                <i class="sidebar-icon md-send"></i>
                Sent Mail
            </a>
        </li>
        <li>
            <a href="#">
                <i class="sidebar-icon md-drafts"></i>
                Drafts
            </a>
        </li>
        <li class="divider"></li>
        <li class="dropdown">
            <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                All Mail
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#" tabindex="-1">
                        Social
                        <span class="sidebar-badge">12</span>
                    </a>
                </li>
                <li>
                    <a href="#" tabindex="-1">
                        Promo
                        <span class="sidebar-badge">0</span>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">
                Trash
                <span class="sidebar-badge">3</span>
            </a>
        </li>
        <li>
            <a href="#">
                Spam
                <span class="sidebar-badge">456</span>
            </a>
        </li>
        <li>
            <a href="#">
                Follow Up
                <span class="sidebar-badge badge-circle">i</span>
            </a>
        </li>
    </ul>
</aside>
<script>
    $(document).ready(function() {
        var overlay = $('.sidebar-overlay');
        $('.sidebar-toggle').on('click', function() {
            var sidebar = $('#sidebar');
            sidebar.toggleClass('open');
            if ((sidebar.hasClass('sidebar-fixed-left') || sidebar.hasClass('sidebar-fixed-right')) && sidebar.hasClass('open')) {
                overlay.addClass('active');
            } else {
                overlay.removeClass('active');
            }
        });
        overlay.on('click', function() {
            $(this).removeClass('active');
            $('#sidebar').removeClass('open');
        });
    });
    $(document).ready(function() {
        var sidebar = $('#sidebar');
        var sidebarHeader = $('#sidebar .sidebar-header');
        var sidebarImg = sidebarHeader.css('background-image');
        var toggleButtons = $('.sidebar-toggle');
        toggleButtons.css('display', 'none');
        $('body').css('display', 'table');
        $('#sidebar-position').change(function() {
            var value = $(this).val();
            sidebar.removeClass('sidebar-fixed-left sidebar-fixed-right sidebar-stacked').addClass(value).addClass('open');
            if (value == 'sidebar-fixed-left' || value == 'sidebar-fixed-right') {
                $('.sidebar-overlay').addClass('active');
            }
            if (value != '') {
                toggleButtons.css('display', 'initial');
                $('body').css('display', 'initial');
            } else {
                toggleButtons.css('display', 'none');
                $('body').css('display', 'table');
            }
        });
        document.getElementById('sidebar-position').value = 'sidebar-fixed-left';
        $('#sidebar-position').change();
    });
    (function($) {
        var dropdown = $('.dropdown');
        dropdown.on('show.bs.dropdown', function(e) {
            $(this).find('.dropdown-menu').first().stop(true, true).slideDown();
        });
        dropdown.on('hide.bs.dropdown', function(e) {
            $(this).find('.dropdown-menu').first().stop(true, true).slideUp();
        });
    }(jQuery));
    (function(removeClass) {
        jQuery.fn.removeClass = function(value) {
            if (value && typeof value.test === 'function') {
                for (var i = 0, l = this.length; i < l; i++) {
                    var elem = this[i];
                    if (elem.nodeType === 1 && elem.className) {
                        elem.className = jQuery.trim(classNames.join(' '));
                    }
                }
            } else {
                removeClass.call(this, value);
            }
            return this;
        };
    }(jQuery.fn.removeClass));
    //@ sourceURL=pen.js
</script>

<select id="sidebar-position" name="sidebar-position" hidden>
    <option value="sidebar-fixed-left">Float on left</option>
    <option value="sidebar-fixed-right">Float on right</option>
</select>

<nav class="navbar navbar-fixed-top header" style="box-shadow: 0px 0px 3px 0px black">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="nav navbar-nav">
                <li class="sidebar-toggle">
                    <a href="#">
                        <i class="glyphicon glyphicon-list"></i>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-bell"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><span class="badge pull-right">40</span>Link</a></li>
                        <li><a href="#"><span class="badge pull-right">2</span>Link</a></li>
                        <li><a href="#"><span class="badge pull-right">0</span>Link</a></li>
                        <li><a href="#"><span class="label label-info pull-right">1</span>Link</a></li>
                        <li><a href="#"><span class="badge pull-right">13</span>Link</a></li>
                    </ul>
                </li>
                <li><a href="#" id="btnToggle"><i class="glyphicon glyphicon-th-large"></i></a></li>
                <li><a href="#"><i class="glyphicon glyphicon-user"></i></a></li>
            </ul>
        </div>
    </div>
</nav>

