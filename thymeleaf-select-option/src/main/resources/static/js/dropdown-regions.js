$(document).ready(function() {
    $("#country").change(function() {
        sendAjaxRequest();
    });
})

function sendAjaxRequest() {
    var countryCode = $("#country").val();
    console.log('country code selected: ', countryCode)
    $.get( "/regions?countryCode=" + countryCode, function( data ) {
        $("#region").empty();
        data.forEach(function(item, i) {
            var option = "<option value = " + item.code + ">" + item.name +  "</option>";
            $("#region").append(option);
        });
    });
};