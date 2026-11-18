document.getElementById('formDepartamento').addEventListener('submit', async function (e) {
    e.preventDefault();

    const form = e.target;
    const departamento = {
        nmDepartamento: form.nmDepartamento.value.trim()
    };

    try {

        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const response = await fetch('/departamentos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${accessToken}`
            },
            body: JSON.stringify(departamento)
        });

        if (response.status === 401) {
            sessionStorage.removeItem('accessToken');
            window.location.replace('/pages/html/login.html');
            return;
        }

        if (response.ok) {
            alert('Departamento cadastrado com sucesso!');
            form.reset();
        } else {
            const errorText = await response.text();
            alert('Erro ao cadastrar: ' + errorText);
        }
    } catch (error) {
        alert('Erro de rede: ' + error.message);
    }
});