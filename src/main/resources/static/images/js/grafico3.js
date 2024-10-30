
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: label,
            datasets: [{
                label: '# de facturas',
                data: point,
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
