function BetRequest(bet, userId, ticketDetails) {
    this.bet = bet;
    this.userId = userId;
    this.ticketDetails = ticketDetails;
}
function TicketDetail(marketId, eventId, seatId, coefficient) {
    this.marketId = marketId;
    this.eventId = eventId;
    this.seatId = seatId;
    this.coefficient = coefficient;
}

var app = angular.module('myApp', []);
app.controller('customersCtrl', function ($http, $interval, $timeout) {
    var ctrl = this;
    ctrl.sum = 0;
    ctrl.userId = 0;
    ctrl.selects = {};

    ctrl.buttonDisabled = false;

    ctrl.betStatus = ["Sucessefull", "Fail"];

    $http.get("/SpringMVC/init").then(function (response) {
        ctrl.languageSettings = response.data;
    });



    ctrl.buttonClick = function () {
        var stop;
        var timeout = $timeout(function () {
            $interval.cancel(stop);
            ctrl.buttonDisabled = false;
            alert(ctrl.betStatus[1]);
        }, 10000);

        ctrl.buttonDisabled = true;


        var ticketDetails = [];
        for (var key in ctrl.selects) {
            if (key != null)
              var ticketDetail = new TicketDetail(
                ctrl.selects[key].marketId,
                key,
                ctrl.selects[key].id,
                ctrl.selects[key].coefficient
            );
            ticketDetails.push(ticketDetail);
        }

        var betRequest = new BetRequest(  ctrl.sum,ctrl.userId, ticketDetails);

        console.log(betRequest);
        $http.post("/SpringMVC/dobet", betRequest).then(function (response) {
            if (response.data["active"] == true) {
                $timeout.cancel(timeout);
                alert("Your previous ticket order hasn't been processed yet! Try again soon.");
                ctrl.buttonDisabled = false;
            }
            else {
                stop = $interval(function () {
                    $http.get("/SpringMVC/checkbet", {
                        params: {
                            transactionId: response.data.transactionId,
                            userId: response.data.userId
                        }
                    })
                        .then(function (response2) {
                            if (response2.data != 2) {
                                $interval.cancel(stop);
                                $timeout.cancel(timeout);
                                ctrl.buttonDisabled = false;
                                alert(ctrl.betStatus[response2.data]);
                            }
                        });
                }, 1000);
            }

        });
    }
});