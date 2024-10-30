
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'polarArea',
        data: {
            labels: label,
            datasets: [{
                label: 'Ganancia',
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
