var MAX_GRID_SIZE = 25;
var DIRECTIONS = [ {x:1,y:0}, {x:0,y:1}, {x:-1,y:0}, {x:0,y:-1} ];
var UP_INDEX=3;

$(document).ready(function() {
    $(".spiralMax").text(MAX_GRID_SIZE * MAX_GRID_SIZE - 1);
    $("#ErrorText").hide();
    $("#SpiralTargetInput").focus();
    makeEnterClickDefaultButton();
    _buildHtmlTableFromGrid(_buildSpiralGrid(MAX_GRID_SIZE));
    _adjustSpiralToDisplayCellsUpTo(-1);
});

function displaySpiral() {
    var n = parseInt($("#SpiralTargetInput").val());
    if (isNaN(n) || (n < 0) || (n > MAX_GRID_SIZE * MAX_GRID_SIZE - 1)) {
        $("#ErrorText").show();
        _adjustSpiralToDisplayCellsUpTo(-1);
        return;
    }
    $("#ErrorText").hide();
    _adjustSpiralToDisplayCellsUpTo(n);
}

// WARNING: Grid is accessed with grid[y][x], not grid[x][y], to make html table construction easier.
function _buildSpiralGrid(gridSize) {
    var grid = new Array(gridSize);
    for (var i=0; i < gridSize; i++) {
        grid[i] = new Array(gridSize);
    }
    var x = Math.floor(gridSize/2);
    var y = x;
    var currentDirectionIndex = 0;
    var stepsInThisDirection = 1;
    var n = 0;
    while (true) {
        for (var stepsRemaining = stepsInThisDirection; stepsRemaining > 0; stepsRemaining--) {
            grid[y][x] = { n:n, shouldHideThisColumnForLowerN: (currentDirectionIndex==UP_INDEX) && (stepsRemaining==stepsInThisDirection) };
            x += DIRECTIONS[currentDirectionIndex].x;
            y += DIRECTIONS[currentDirectionIndex].y;
            n++;
            if (n >= gridSize * gridSize) {
                return grid;
            }
        }
        if (currentDirectionIndex % 2) {
            stepsInThisDirection++;
        }
        currentDirectionIndex = (currentDirectionIndex + 1) % 4;
    }
}

function _buildHtmlTableFromGrid(grid) {
    var table = $("#SpiralTable");
    table.empty();
    $.each(grid, function(y, row) {
        var tableRow = $("<tr></tr>");
        $.each(row, function(x, cellContents) {
            var tableCell = $("<td></td>");
            if (!x) {
                tableCell.addClass("leftColumn");
            }
            tableCell.data("n", cellContents.n);
            if (cellContents.shouldHideThisColumnForLowerN) {
                var firstCellInThisColumn = $("#SpiralTable tr:first td:nth-child(" + (x+1) + ")");
                firstCellInThisColumn.data("hideIfNLessThan", cellContents.n);
                firstCellInThisColumn.addClass("hidableColumn");
            }
            tableRow.append(tableCell);
        });
        table.append(tableRow);
    });
}

function _adjustSpiralToDisplayCellsUpTo(n) {
    _setTextForSpiralCells(n);
    _hideColumnsToLeftOfAllValidCells(n);
    _hideRowsWithNoFilledCells();
}

function _setTextForSpiralCells(n) {
    $("#SpiralTable td").each( function() {
        var cellValue = $(this).data("n");
        $(this).text((cellValue <= n) ? cellValue : "");
        $(this).show();
    });
}

function _hideColumnsToLeftOfAllValidCells(n) {
    $("#SpiralTable .hidableColumn").each( function(index) {
        var hideIfNLessThan = $(this).data("hideIfNLessThan");
        if (n < hideIfNLessThan) {
            _hideColumn(index);
        }
    });
}

function _hideRowsWithNoFilledCells() {
    $("#SpiralTable tr").show();
    $("#SpiralTable tr").filter( function() {
        return $(this).find("td:not(:empty)").length == 0;
    }).hide();
}

function _hideColumn(columnIndex) {
    var numberForNthChild = columnIndex + 1;
    $("#SpiralTable td:nth-child(" + numberForNthChild + ")").hide();
}
