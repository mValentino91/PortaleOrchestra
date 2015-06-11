
<%-- 
    Document   : anmStopInfo
    Created on : 3-giu-2015, 10.11.59
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    function getInformation() {
        var code = $('#code').val().trim();

        $('#result').html('');
        $('#resultlinee').html('');
        if (code != '') {
            $('#loading').show();
            $.ajax({
                dataType: "json",
                url: './anmServices/stops/allInfo/' + code
            }).done(function (data) {
                $('#loading').hide();
                var json = data;
                if (json.error === undefined) {
                    $('#resultlinee').append(
                            '<br><b>Nome:</b> ' + json.nome
                            + '<br><b>Linee:</b> '
                            );
                    for (var i = 0; i < json.transiti.length; i++) {
                        $('#resultlinee').append(json.transiti[i] + ' ');
                    }
                    $('#result').append('<b>Previsioni:</b> ');
                    for (var i = 0; i < json.previsioni.length; i++) {
                        $('#result').append('<br>' + json.previsioni[i].codice + ' - ' + json.previsioni[i].time);
                    }
                    setTimeout(function () {
                        $(".nano").nanoScroller();
                    }, 500);
                } else {
                    $('#result').append('Informazioni sulla fermata non disponibili!');
                    setTimeout(function () {
                        $(".nano").nanoScroller();
                    }, 500);
                }
            });
        }
        else {
            $('#result').append('Specificare un codice!');
        }
    }
</script>
<style>
    .comp-header{
        height: 40px;

        max-height: 40px;
        text-align: center;
        color: #000000;
        font-weight: bold;
    }
</style>

    <div class="col-md-6 col-orc" >
        <div class="box-orc">
            <div class="box-elem component component-text" style=" overflow: hidden;">
                <div class="details">
                    <div class="comp-header" style='margin-top: 0!important;'>
                        <div style="font-size: 120%; display: inline">Ricerca Palina</div>
                        <img src="./dist/img/anm.jpg" style="display: inline; float: right; max-height: 28px;">

                    </div>

                    <div class="input-group">
                        <input id="code" type="text" class="form-control" placeholder="Inserire Codice Fermata">
                        <span class="input-group-btn">
                            <button class="btn btn-default" onclick="getInformation()" type="button">Cerca</button>
                        </span>
                    </div>
                    <div id="resultlinee"></div>
                    <div  style="height: 250px; padding: 15px;">
                        <center>
                            <div class="nano">
                                <div class="nano-content">
                                    <div id="result" style="text-align: center;">   
                                    </div>
                                    <img id="loading" style="display: none; height: 50px; margin-top:30px;" src="./dist/img/loading.gif"/>

                                </div>
                            </div>
                        </center>
                    </div>

                </div>
            </div>

        </div>


    </div>
<div class="col-md-4"></div>



