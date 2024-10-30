
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: label,
            datasets: [{
                label: 'Stock por ropa',
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
