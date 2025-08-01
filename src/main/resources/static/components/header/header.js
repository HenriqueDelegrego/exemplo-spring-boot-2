fetch('../../components/header/header.html')
    .then(response => response.text())
    .then(header => {
        document.getElementById('header').innerHTML = header;
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '../../components/header/header-style.css';
        document.head.appendChild(link);
    })
    .catch(error => {
        document.getElementById('header').innerHTML = `<p>Erro ao carregar o header</p>`;
    });
