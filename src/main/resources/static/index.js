


$("#resultsButton").click(function(){

var csvData;
var results;
var xLaser =[];
var yLaser = [];
var zLaser = [];
var resultsRequest = new Request('/api/results',{
             method: 'GET'
         });
            fetch(resultsRequest).then( function(resp){
                resp.json().then(function(data) {
                    console.log(data);
                  results = data;

                    $('#headerRow').css('display', 'inline-block');
                    $(".shipData").remove();
                    for(var i =0;i<results.shipSize.length;i++){
                        var name = "<td class ='center'>"+results.shipSize[i].name+"</td>";
                        var size = "<td class ='center'>"+results.shipSize[i].size+"</td>";
                        $('#headerRow').append("<tr class='shipData'>"+name+size+"<tr>");
                    }
                    $("#numberofShips").text("Number of Ships: " + results.numberOfShips);
                    $("#destructionScore").text("Destruction Score: " + results.destructionScore);
                    $("#startingPoint").text("Laser Starting Point: " + results.startingPoint);
                    $("#vector").text("Vector: " + results.vector);
                    $("#shipsDestroyed").text("Ships Destroyed: " + results.shipsDestroyed);
                    if(results.vector[0]==1){
                        for(var i =0; i<18;i++){
                            xLaser.push(i);
                            yLaser.push(results.startingPoint[1]);
                            zLaser.push(results.startingPoint[2]);
                        }
                    }
                    else if(results.vector[1]==1){
                        for(var i =0; i<18;i++){
                            yLaser.push(i);
                            xLaser.push(results.startingPoint[0]);
                            zLaser.push(results.startingPoint[2]);
                        }
                    }
                    else if(results.vector[2]==1){
                        for(var i =0; i<18;i++){
                            zLaser.push(i);
                            yLaser.push(results.startingPoint[1]);
                            xLaser.push(results.startingPoint[0]);
                        }
                    }
                    Plotly.d3.csv('/api/grid.csv', function(err, rows){

                        function unpack(rows, key) {
                            return rows.map(function(row) { return row[key]; });
                        }

                        var data = [{
                            name:'Ships',
                            x: unpack(rows, 'x'),
                            y: unpack(rows, 'y'),
                            z: unpack(rows, 'z'),
                            mode: 'markers',
                            type: 'scatter3d',
                            marker: {
                                line: {
                                    color: 'rgb(0, 0, 0)',
                                    width: 2},
                                color: 'rgb(0, 0, 100)',
                                size: 10,
                                symbol: 'square'
                            }
                        },
                            {
                                x: xLaser,
                                y: yLaser,
                                z: zLaser,
                                name:'Laser',
                                mode: 'lines',
                                type: 'scatter3d',
                                line: {
                                    color: 'rgb(255, 0, 0)',
                                    width: 20}

                            }


                        ];

                        var layout = {
                            autosize: false,
                            height: 1000,
                            scene: {


                                xaxis: {
                                    type: 'linear',
                                    zeroline: false,
                                    fixedrange:true
                                },
                                yaxis: {
                                    type: 'linear',
                                    zeroline: false,
                                    fixedrange:true
                                },
                                zaxis: {
                                    type: 'linear',
                                    zeroline: false,
                                    fixedrange:true
                                }
                            },
                            title: 'Space Ship Map',
                            width: 1000
                        };

                        Plotly.newPlot('myDiv', data, layout);

                    });
                });
            }).catch(err =>{
            console.log(err);
            });
});




