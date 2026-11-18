async function carregarDepartamentoPorId() {

    const id = new URLSearchParams(window.location.search).get('id');

    try {
        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const response = await fetch(`/departamentos/${id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        if (response.status === 401) {
            sessionStorage.removeItem('accessToken');
            window.location.replace('/pages/html/login.html');
            return;
        }

        if (!response.ok) {
            throw new Error('Erro ao buscar departamento');
        }

        const departamento = await response.json();

        document.getElementById('idDepartamento').value = departamento.idDepartamento;
        document.getElementById('nome').value = departamento.nmDepartamento;

    } catch (error) {
        console.error('Erro ao carregar departamento:', error);
    }
}

carregarDepartamentoPorId();