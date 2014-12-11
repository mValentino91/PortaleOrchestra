<%-- 
    Document   : topBar
    Created on : 6-dic-2014, 16.29.15
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="matnavbar matnavbar-inverse">
    <div class="matnavbar-header">
        <button type="button" class="matnavbar-toggle" data-toggle="collapse" data-target=".matnavbar-inverse-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="matnavbar-brand mdi-navigation-menu sidebar-toggle" href="javascript:void(0)">ORCHESTRA</a>
    </div>
    <div class="matnavbar-collapse collapse matnavbar-inverse-collapse">
        <ul class="nav matnavbar-nav">
            <li class="active"><a href="javascript:void(0)">Active</a></li>
            <li><a href="javascript:void(0)">Link</a></li>
            <li class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">Action</a></li>
                    <li><a href="javascript:void(0)">Another action</a></li>
                    <li><a href="javascript:void(0)">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Dropdown header</li>
                    <li><a href="javascript:void(0)">Separated link</a></li>
                    <li><a href="javascript:void(0)">One more separated link</a></li>
                </ul>
            </li>
        </ul>
        <form class="matnavbar-form matnavbar-left">
            <input type="text" class="form-control col-lg-8" placeholder="Search">
        </form>
        <ul class="nav matnavbar-nav matnavbar-right">
            <li><a href="javascript:void(0)">Link</a></li>
            <li class="dropdown">
                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)">Action</a></li>
                    <li><a href="javascript:void(0)">Another action</a></li>
                    <li><a href="javascript:void(0)">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="javascript:void(0)">Separated link</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
