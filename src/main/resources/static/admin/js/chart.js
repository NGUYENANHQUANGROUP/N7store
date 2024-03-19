    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";

    document.addEventListener('DOMContentLoaded', function () {
        // Dữ liệu cho biểu đồ
        var data = {
            labels: ["2016", "2017", "2018", "2019", "2020", "2021", "2022"],
            datasets: [{
                    label: "UCANA",
                    data: [15, 30, 55, 65, 60, 80, 95],
                    backgroundColor: "rgba(235, 22, 22, .7)"
                },
                {
                    label: "UK",
                    data: [8, 35, 40, 60, 70, 55, 75],
                    backgroundColor: "rgba(235, 22, 22, .5)"
                },
                {
                    label: "AU",
                    data: [12, 25, 45, 55, 65, 70, 60],
                    backgroundColor: "rgba(235, 22, 22, .3)"
                }
            ]
        };
    
        // Tùy chọn của biểu đồ
        var options = {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        };
    
        // Lấy thẻ canvas và vẽ biểu đồ
        var ctx = document.getElementById('worldwide-sales').getContext('2d');
        var myBarChart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    });
    