var evaluation = (function() {
//Questo script serve per il sistema di valutazione a 5 stelle
// faccio il preload dell'immagine utilizzata per l'effetto rollover
    var staron;
    var avarageDiv;
    var evaluationDiv;

    function sendEvaluation(rating) {

        //controllo che i parametri siano validi
        if (rating !== null && rating !== undefined && rating > 0)
        {
            //nuovo oggetto XMLHttpRequest
            var ajax = new XMLHttpRequest();

            // se l’oggetto XMLHttpRequest non è nullo
            if (ajax)
            {
                // impostazione richiesta asincrona in Post
                // del file specificato
                ajax.open('post', './InsertNewEvaluationController', true);

                // imposto gli header per il metodo POST
                ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded");

                // impostazione controllo e stato della richiesta
                ajax.onreadystatechange = function()
                {
                    // verifica dello stato
                    if (ajax.readyState === 4)
                    {
                        //risposta ricevuta dal server
                        // verifica della risposta da parte del server
                        if (ajax.status === 200)
                        {
                            // risposta ricevuta con successo
                            // aggiorno la valutazione media degli utenti. Il server risponde inviando la valutazione media aggiornata  
                            evaluationDiv.innerHTML = "Valutazione inviata con successo!";
                        }
                        else
                        {
                            //codice d'errore inviato dal server
                            bootbox.alert("<b>Attenzione:</b> Impossibile inviare la valutazione. Riprovare");
                            evaluation(0);
                        }
                    }
                };
                // invio la richiesta
                ajax.send('evaluation=' + rating + "&idPoi=" +index_markeref);
            }
        }
        else
        {
            bootbox.alert("<b>Attenzione:</b> Impossibile inviare la valutazione. Riprovare");
            evaluation(0);
        }
    }

    function initEvaluation(canEvaluate, evaluationDivId)
    {
        if (canEvaluate)
        {
            evaluationDiv = document.getElementById(evaluationDivId);
            evaluation(0);
        }

    }
// Definisco la funzione per "accendere" dinamicamente le stelle
// unico argomento è il numero di stelle da accendere
    function star_on(QT)
    {
        // verifico che esistano i DIV delle stelle
        // se il DIV non esiste significa che si è già votato
        if (document.getElementById('star_1'))
        {
            var i;
            // Ciclo tutte e 5 i DIV contenenti le stelle
            for (i = 1; i <= 5; i++)
            {
                // se il div è minore o uguale del numero di stelle da accendere
                // imposto dinamicamente la classe su "on"
                if (i <= QT)
                    document.getElementById('star_' + i).className = "fa fa-star fa-lg";
                // in caso contrario spengo la stella...
                else
                    document.getElementById('star_' + i).className = "fa fa-star-o fa-lg";
            }
        }
    }

// Questa è la funzione che produce l'output.
// richiede come unico argomento il numero di stelle che si vuole accendere
    function evaluation(QT)
    {
        // stampo il codice HTML che produce le stelle 
        evaluationDiv.innerHTML +=
                '<i style="color:gold; cursor:pointer" id="star_1" class="fa fa-star-o fa-lg"  onclick="evaluation.sendEvaluation(' + "'1'" + ')" onmouseover="evaluation.star_on(0); evaluation.star_on(1)"></i>' +
                '<i style="color:gold; cursor:pointer" id="star_2" class="fa fa-star-o fa-lg"  onclick="evaluation.sendEvaluation(' + "'2'" + ')" onmouseover="evaluation.star_on(0); evaluation.star_on(2)"></i>' +
                '<i style="color:gold; cursor:pointer" id="star_3" class="fa fa-star-o fa-lg"  onclick="evaluation.sendEvaluation(' + "'3'" + ')" onmouseover="evaluation.star_on(0); evaluation.star_on(3)"></i>' +
                '<i style="color:gold; cursor:pointer" id="star_4" class="fa fa-star-o fa-lg"  onclick="evaluation.sendEvaluation(' + "'4'" + ')" onmouseover="evaluation.star_on(0); evaluation.star_on(4)"></i>' +
                '<i style="color:gold; cursor:pointer" id="star_5" class="fa fa-star-o fa-lg"  onclick="evaluation.sendEvaluation(' + "'5'" + ')" onmouseover="evaluation.star_on(0); evaluation.star_on(5)"></i>';

        // accendo le stelle definite in argomento
        star_on(QT);
    }

    //Return the id list for the functions to call
    return {
        init: initEvaluation,
        star_on: star_on,
        evaluation: evaluation,
        sendEvaluation: sendEvaluation
    };

})();