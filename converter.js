var DOLLAR_REGEX = /^\$?(\d{1,3}(\,\d{3})*|(\d+))(\.\d{2})?$/

var NUMBERS_TO_NINETEEN = [ "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eightteen", "nineteen" ];
var TENS = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
var HUNDRED = "hundred";
var THOUSAND = "thousand";
var MILLION = "million";
var BILLION = "billion";

function convertToText() {
    var rawValue = $("#dollarInput").val();
    if (!DOLLAR_REGEX.test(rawValue)) {
        $("#Result").text("I'm sorry, I can't interpret that as a dollar amount.");
        return;
    }
    $("#Result").text(convertDollarStringToWords(rawValue));
}

function convertDollarStringToWords(rawValue) {
    if (rawValue.substr(0, 1) == "$") {
        rawValue = rawValue.substr(1);
    }
    rawValue = rawValue.replace(/\,/g, '');
    if (rawValue.indexOf(".") < 0) {
        rawValue = rawValue + ".00";
    }
    var splitValue = rawValue.split(".");
    var dollarsAsInt = parseInt(splitValue[0]);
    if (dollarsAsInt > 999999999999) {
        return ("Too rich for my blood!");
    }
    var dollarPart = convertIntegerToWords(dollarsAsInt);
    dollarPart = dollarPart.substr(0, 1).toUpperCase() + dollarPart.substr(1);
    var centsPart = splitValue[1] + "/100 dollars";
    return dollarPart + (dollarPart ? " and " : "") + centsPart;
}

function convertIntegerToWords(value) {
    var valueAndResult = { value: value, result: "" };
    convertSegment(valueAndResult, 1000000000, BILLION);
    convertSegment(valueAndResult, 1000000, MILLION);
    convertSegment(valueAndResult, 1000, THOUSAND);
    convertSegment(valueAndResult, 1, "");
    return valueAndResult.result;
}

function convertSegment(valueAndResult, divisor, name) {
    if (valueAndResult.value >= divisor) {
        var numberOfTheseUnits = Math.floor(valueAndResult.value / divisor);
        valueAndResult.result += _convertUpToThreeDigitsToWords(numberOfTheseUnits);
        if (name) {
            valueAndResult.result += " " + name;
        }
        valueAndResult.value = valueAndResult.value % divisor;
        if (valueAndResult.value) {
            valueAndResult.result += " ";
        }
    }
}

function _convertUpToThreeDigitsToWords(value) {
    var result = "";
    if (value >= 100) {
        result = NUMBERS_TO_NINETEEN[Math.floor(value / 100)] + " " + HUNDRED;
        value = value % 100;
        if (value) {
            result += " ";
        }
    }
    if (value <= 19) {
        return result + NUMBERS_TO_NINETEEN[value];
    }
    result += TENS[Math.floor(value / 10)];
    value = value % 10;
    if (value) {
        result += "-";
    }
    return result + NUMBERS_TO_NINETEEN[value];
}