const form = document.getElementById('form-editar');

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    await atualizarDepartamento();
});

async function atualizarDepartamento() {
    try {
        const id = new URLSearchParams(window.location.search).get('id');

        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const response = await fetch(`/departamentos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${accessToken}`
            },
            body: JSON.stringify({
                nmDepartamento: document.getElementById('nome').value
            })
        });

        if (!response.ok) {
            throw new Error('Erro ao atualizar departamento');
        }
        window.location.href = '../html/lista-departamentos.html';
    } catch (error) {
        console.error('Erro ao atualizar departamento:', error);
    }
}