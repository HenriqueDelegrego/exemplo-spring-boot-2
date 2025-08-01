
fetch('../../components/header/header.html')
    .then(response => response.text())
    .then(header => {
        document.getElementById('header').innerHTML = header;
    })
    .catch(error => {
        document.getElementById('header').innerHTML = `<p>Erro ao carregar o header</p>`;
    });
