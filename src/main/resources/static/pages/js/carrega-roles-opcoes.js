async function carregarRoles() {
    try {

        const accessToken = sessionStorage.getItem('accessToken');

        const response = await fetch(`/api/roles`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        }); if (!response.ok) throw new Error('Erro ao buscar roles');

        const roles = await response.json();
        const select = document.getElementById('roles');

        roles.forEach(role => {
            const option = document.createElement('option');
            option.value = role;
            option.textContent = role;
            select.appendChild(option);
        });
    } catch (error) {
        alert('Erro ao carregar roles: ' + error.message);
    }
}

carregarRoles();