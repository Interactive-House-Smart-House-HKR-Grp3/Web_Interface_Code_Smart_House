const ctx = document.getElementById('chart-electricity').getContext('2d');
                        const myChart = new Chart(ctx, {
                            type: 'line', // bar, horizontalBar, pie, line, doughnut, polarArea
                             data: {

                                labels: labels,

                                datasets: [

                                    {

                                        label: "My First dataset",

                                        fill: false,

                                        lineTension: 0.1,

                                        backgroundColor: "rgba(75, 192, 192, 0.4)",

                                        borderColor: "rgba(75, 192, 192, 1)",

                                        borderCapStyle: 'butt',

                                        borderDash: [],

                                        borderDashOffset: 0.0,

                                        borderJoinStyle: 'miter',

                                        pointBorderColor: "rgba(75,192,192,1)",

                                        pointBackgroundColor: "#fff",

                                        pointBorderWidth: 1,

                                        pointHoverRadius: 5,

                                        pointHitRadius: 10,

                                        data: data,

                                    }

                                ]
                            },
                            options: {
                                scales: {
                                    yAxes: [{
                                        ticks: {
                                            beginAtZero: true
                                        }
                                    }]
                                },

                                layout: {
                                    padding: {
                                        left: 50,
                                        right: 50,
                                        top: 0,
                                        bottom: 50
                                    }
                                },

                                legend: {
                                    display: false
                                }
                            }
                        });