/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getWeather() {
    $.ajax({
        type: "GET",
        url: "./getCurrentWeather",
        success: function (result, stato) {
            var meteo = JSON.parse(result);
           $("#weather img").attr("src", "./dist/img/weather/" + meteo.weatherCode + ".png");
        },
        error: function (richiesta, stato, errori) {
            alert("Error. State: " + stato);
        }
    });


}