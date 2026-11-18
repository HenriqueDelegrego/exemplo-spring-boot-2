async function carregarDepartamentos() {
    try {
        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const response = await fetch('/departamentos', {
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
            throw new Error('Erro ao buscar departamentos');
        }

        const departamentos = await response.json();

        const lista = document.getElementById('departamentos');
        lista.innerHTML = '';

        departamentos.forEach(departamento => {
            const div = document.createElement('div');
            div.className = 'departamento';
            div.innerHTML = `
                <span><strong>Nome:</strong> ${departamento.nmDepartamento}</span>
                <span><strong>ID:</strong> ${departamento.idDepartamento}</span>
                <div class="acoes">
                    <button class="btn-editar" title="Editar" onclick="editarDepartamento(${departamento.idDepartamento})">✏️</button>
                    <button class="btn-excluir" title="Excluir" onclick="confirmarExclusao(${departamento.idDepartamento})">❌</button>
                </div>
            `;
            lista.appendChild(div);
        });

        ocultarAcoesSeNaoGerente();
    } catch (error) {
        console.error('Erro ao carregar departamentos:', error);
    }
}

async function ocultarAcoesSeNaoGerente() {
    try {
        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const response = await fetch('/funcionarios/me', {
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
            throw new Error('Erro ao buscar funcionário');
        }

        const funcionario = await response.json();

        if (funcionario.role !== 'GERENTE') {
            const acoesElements = document.querySelectorAll('.acoes');
            acoesElements.forEach(element => {
                element.style.display = "none";
            });
        }
    } catch (error) {
        console.error('Erro ao verificar se o usuário é gerente:', error);
    }
}

function editarDepartamento(id) {
    window.location.href = `edicao-departamento.html?id=${id}`;
}

async function confirmarExclusao(id) {
    const confirmacao = confirm("Tem certeza que deseja excluir este departamento?");
    if (!confirmacao) return;
    try {

        console.log(`Excluindo departamento com ID: ${id}`);

        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const response = await fetch(`/departamentos/${id}`, {
            method: 'DELETE',
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
            throw new Error('Erro ao excluir departamento');
        }

        if (response.ok) {
            window.location.reload();
        } else {
            const erro = await response.text();
            alert("Erro ao excluir: " + erro);
        }
    } catch (erro) {
        alert("Erro de rede ao excluir: " + erro.message);
    }
}

carregarDepartamentos();