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
<aside id="sidebar" class="sidebar sidebar-colored open" role="navigation">
    <!-- Sidebar header -->
    <div class="sidebar-header header-cover" style="">
        <!-- Top bar -->
        <div class="top-bar"></div>
        <!-- Sidebar toggle button -->
        <button type="button" class="sidebar-toggle btn btn-default">
            <span class="fa fa-times"
                  style="color: white"></span>
        </button>
        <!-- Sidebar brand image -->
        <div class="sidebar-image">
            <img src="./dist/img/logo.png">
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
                <span class="glyphicon glyphicon-home"></span>
                Home
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <div id="categories" class="test"></div>
        </li>
</aside>
<script src="./dist/js/bootstrap-treeview.js"></script>
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

    $(function() {

        var menu = '[ { "slug": "culture", "text": "Cultura", "nodes": [ {"slug": "museum", "text": "Musei"}, {"slug": "monument", "text": "Monumenti", "nodes": [{"slug": "church", "text": "Chiese"},{"slug": "building", "text": "Palazzi Storici"},{"slug": "sculpture", "text": "Sculture"}]}, {"slug": "metro_art", "text": "Stazioni dell'+"'"+'Arte"}]}, { "slug": "food", "text": "Enogastronomia", "nodes": [ {"slug": "coffee", "text": "Caffé"}, {"slug": "trattoria", "text": "Trattorie"}, {"slug": "pasticceria", "text": "Pasticcerie"},{"slug": "pizzeria", "text": "Pizzerie"}]}]';

        $('#categories').treeview({
            data: menu,
            showBorder: false,
            levels: 1,
            onNodeSelected: function(event, node) {
                // alert('<p>' + node.text + '[' + node.id_cat + ']' + '</p>');
            }

        });


    });
</script>

<select id="sidebar-position" name="sidebar-position" hidden>
    <option value="sidebar-fixed-left">Float on left</option>
    <option value="sidebar-fixed-right">Float on right</option>
</select>

<nav class="navbar navbar-fixed-top header" style="background-color: #285e8e; box-shadow: 0px 0px 3px 0px black">
    <div class="container-fluid" style="background-color: #285e8e;">
        <div class="navbar-header">
            <button type="button" 
                    class="navbar-toggle collapsed"
                    data-toggle="collapse" 
                    data-target="#navbar" 
                    aria-expanded="false" 
                    aria-controls="navbar">
                <span class="fa fa-plus-square fa-lg" style="color: white;"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="navbar" style="background-color: #285e8e;">
            <ul class="nav navbar-nav">
                <li class="sidebar-toggle">
                    <a href="#">
                        <i class="fa fa-bars fa-lg"></i>
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

