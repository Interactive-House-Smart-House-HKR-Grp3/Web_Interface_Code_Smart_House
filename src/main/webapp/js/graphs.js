Chart.defaults.global.defaultFontFamily = 'UnicaOne';
Chart.defaults.global.tooltips.backgroundColor = '#44C4BF';
Chart.defaults.global.animation.easing = 'easeInOutQuad';


const ctx = document.getElementById('chart-electricity').getContext('2d');
                        const myChart = new Chart(ctx, {
                            type: 'line', // bar, horizontalBar, pie, line, doughnut, polarArea
                             data: {

                                labels: labels,

                                datasets: [

                                    {
                                        label: "Electricity Consumption",

                                        fill: false,

                                        lineTension: 0.1,

                                        backgroundColor: "rgba(252, 207, 3, 0.4)",

                                        borderColor: "rgba(252, 207, 3, 1)",

                                        borderCapStyle: 'butt',

                                        borderDash: [],

                                        borderDashOffset: 0.0,

                                        borderJoinStyle: 'miter',

                                        pointBorderColor: "rgba(252, 207, 3,1)",

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
                                    
                                },

                                legend: {
                                    display: false
                                }
                            }
                        });