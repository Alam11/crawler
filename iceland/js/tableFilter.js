$.getJSON( "data/table.json", function( data ) {
    s ='';
    for(var j = 0; j < data[0].length;j++){
        s+='<th>'+data[0][j]+'</th>';
    }
    $('thead').append('<tr>'+s+'</tr>');
    s = '';
    for(var i = 1; i < data.length; i++){
        var temp = data[i]
        s ='';
        for(var j = 0; j < temp.length;j++){
            s+='<td>'+temp[j]+'</td>';
        }
        $('tbody').append('<tr>'+s+'</tr>');
    }

    $('th').click(function(){
        var table = $(this).parents('table').eq(0)
        var rows = table.find("tr:not(:has('th'))").toArray().sort(comparer($(this).index()))
        this.asc = !this.asc
        if (!this.asc){rows = rows.reverse()}
        for (var i = 0; i < rows.length; i++){table.append(rows[i])}
    });

    function comparer(index) {
        return function(a, b) {
            var valA = getCellValue(a, index), valB = getCellValue(b, index)
            return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB)
        }
    };

    function getCellValue(row, index){ return $(row).children('td').eq(index).html() };


    $('table').each(function(){
        var table = $(this)
        var headers = table.find('th').length
        var filterrow = $('<tr>')
        for (var i = 0; i < headers; i++){
            filterrow.append($('<th>').append($('<input>').attr('type','text').keyup(function(){
                table.find('tr').show()
                filterrow.find('input[type=text]').each(function(){
                    var index = $(this).parent().index() + 1;
                    var filter = $(this).val() != ''
                    $(this).toggleClass('filtered', filter)
                    if (filter){
                        var el = 'td:nth-child('+index+')'
                        var criteria = ":contains('"+$(this).val()+"')"
                        table.find(el+':not('+criteria+')').parent().hide()
                    }
                })
            })))
        }
        $('tfoot').append(filterrow);
    })

});
