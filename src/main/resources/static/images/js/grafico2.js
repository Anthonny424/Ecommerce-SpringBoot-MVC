
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: label,
            datasets: [{
                label: '# de stock',
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
