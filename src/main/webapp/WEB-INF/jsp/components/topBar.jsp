<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<style>


    .navbar-default{
        border: 0;
    }
    .navbar{
        background-color:#285E8E;
        border-radius: 0;
    }

    .navbar-default .navbar-nav > li > a{
        color: #fff;
        height: 50px !important;
    }

    .navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus {
        background-color: #285E8E;
        color: #fff;
    }

    .dropdown{
        background-color: #285E8E;
        color: #fff;
    }

    .profile-image{
        height: 25px !important;
        width: 25px !important;   
        margin-top: -3px;
    }
    .profile-image-loading {
        height: 25px !important;
        width: 25px !important;
        margin-right: 15px !important;
        margin-top: 12x !important;
    }

    .navbar-brand{
        padding-top: 0px;
        padding-left: 5px;
    }

    #loginArea{
        margin-right: -15px;
        margin-left: 10px;
    }
    .autocomplete-suggestions { border-radius: 0px 0px 4px 4px; border: 1px solid rgba(0,0,0,0.15); background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);   box-shadow: 0 6px 12px rgba(0,0,0,0.175); }
    .autocomplete-suggestion { border-top: 1px solid rgba(0,0,0,0.15); padding: 5px 10px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; }
    .autocomplete-no-suggestion { padding: 2px 5px;}
    .autocomplete-selected { background: whitesmoke; }
    .autocomplete-suggestions strong { font-weight: bold; color: #00689a; }
    .autocomplete-group { padding: 2px 5px; }
    .autocomplete-group strong { font-weight: bold; font-size: 16px; color: #00689a; display: block; }
</style>
<script src="./dist/js/jquery.autocomplete.js"></script>

</head>

<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="./"><img height="50" alt="Home" src="./dist/img/logoOrchestra.png"></a> 
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="./Map?category=all">Mappa Interattiva</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categorie <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Cultura</a></li>
                            <li><a href="#">Pernottamento</a></li>
                            <li><a href="#">Enogastronomia</a></li>
                            <li><a href="#">Artigiani</a></li>
                            <li><a href="#">Mobilità</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Eventi</a></li>
                            <li><a href="#">Associazioni culturali</a></li>
                            <li><a href="#">Expo Napoli</a></li>
                        </ul>
                    </li>
                </ul>


                <ul class="nav navbar-nav navbar-right">
                    
                    <li>
                        
                        <input style="margin-top: 8px" id="autocomplete" type="text" class="form-control" placeholder="Cerca...">
                    </li>
                    <li id="loginArea" class="dropdown">
                        <jsp:include page="../access/loginArea.jsp" />
                    </li>
                    
                </ul> 

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <script>
        $('#autocomplete').autocomplete({
            serviceUrl: './Search/Autocomplete',
            onSelect: function (suggestion) {
                window.location = './getPoi?id=' + suggestion.data;
            }
        });
    </script>