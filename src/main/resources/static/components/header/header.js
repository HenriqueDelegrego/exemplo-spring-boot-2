async function ocultarAcoes() {
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
        console.error(error);
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







fetch('../../components/header/header.html')
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar o cabeçalho.');
        }
        return response.text();
    })
    .then(header => {
        document.getElementById('header').innerHTML = header;

        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '../../components/header/header-style.css';
        document.head.appendChild(link);

        // Adiciona o evento de pesquisa após carregar o header
        const formPesquisa = document.getElementById('formulario-pesquisa');

        if (!formPesquisa) return;

        formPesquisa.addEventListener('submit', async function (e) {
            e.preventDefault();

            let pesquisa = document.getElementById('input-pesquisa').value.trim().toLowerCase();

            const containerFuncionarios = document.getElementById('funcionarios');
            const containerDepartamentos = document.getElementById('departamentos');

            const accessToken = sessionStorage.getItem('accessToken');

            if (!accessToken) {
                window.location.replace('/pages/html/login.html');
                return;
            }

            // Pesquisa de funcionários
            if (containerFuncionarios) {
                containerFuncionarios.innerHTML = '';

                try {
                    const response = await fetch('/funcionarios', {
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
                        throw new Error('Erro ao buscar funcionários.');
                    }

                    const funcionarios = await response.json();

                    // Filtra os funcionários conforme a pesquisa
                    const funcionariosFiltrados = funcionarios.filter(func => {
                        return (
                            func.nome?.toLowerCase().includes(pesquisa) ||
                            func.cpf?.toLowerCase().includes(pesquisa) ||
                            func.email?.toLowerCase().includes(pesquisa)
                        );
                    });

                    if (funcionariosFiltrados.length === 0) {
                        containerFuncionarios.innerHTML =
                            '<p>Nenhum funcionário encontrado.</p>';
                        return;
                    }

                    funcionariosFiltrados.forEach(func => {
                        const div = document.createElement('div');
                        div.className = 'funcionario';

                        div.innerHTML = `
                            <div class="acoes">
                                <button class="btn-editar" title="Editar"
                                    onclick="editarFuncionario(${func.idFuncionario})">✏️</button>

                                <button class="btn-excluir" title="Excluir"
                                    onclick="confirmarExclusao(${func.idFuncionario})">❌</button>
                            </div>

                            <strong>Nome:</strong> <span>${func.nome}</span>
                            <strong>CPF:</strong> <span>${func.cpf}</span>
                            <strong>Email:</strong> <span>${func.email}</span>

                            <strong>Data de Nascimento:</strong>
                            <span>${func.dataNascimento ? new Date(func.dataNascimento).toLocaleDateString('pt-BR') : 'N/A'}</span>

                            <strong>Salário:</strong>
                            <span>R$ ${func.salario?.toFixed(2) || '0.00'}</span>

                            <strong>Função:</strong>
                            <span>${func.role === 'GERENTE' ? 'Gerente' : 'Funcionário'}</span>

                            <strong>Departamento:</strong>
                            <span>${func.departamento?.nmDepartamento || 'N/A'}</span>

                            <strong>Criado por:</strong>
                            <span>${func.criadoPor || 'N/A'}</span>

                            <strong>Endereço:</strong>
                            <span>${func.endereco?.logradouro || ''}, ${func.endereco?.numero || ''} - ${func.endereco?.bairro || ''}</span>

                            <span>${func.endereco?.cidade || ''} - ${func.endereco?.estado || ''}</span>
                            <span>CEP: ${func.endereco?.cep || ''}</span>
                        `;

                        containerFuncionarios.appendChild(div);
                    });

                    ocultarAcoes();

                } catch (error) {
                    containerFuncionarios.innerHTML =
                        `<p style="color: red;">Erro ao pesquisar funcionários: ${error.message}</p>`;
                }
            }

            // Pesquisa de departamentos
            else if (containerDepartamentos) {
                containerDepartamentos.innerHTML = '';

                try {
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
                        throw new Error('Erro ao buscar departamentos.');
                    }

                    const departamentos = await response.json();

                    // Filtra os departamentos conforme a pesquisa
                    const departamentosFiltrados = departamentos.filter(departamento => {
                        return (
                            departamento.nmDepartamento?.toLowerCase().includes(pesquisa) ||
                            String(departamento.idDepartamento).includes(pesquisa)
                        );
                    });

                    if (departamentosFiltrados.length === 0) {
                        containerDepartamentos.innerHTML =
                            '<p>Nenhum departamento encontrado.</p>';
                        return;
                    }

                    departamentosFiltrados.forEach(departamento => {
                        const div = document.createElement('div');
                        div.className = 'departamento';

                        div.innerHTML = `
                            <span>
                                <strong>Nome:</strong> ${departamento.nmDepartamento}
                            </span>

                            <span>
                                <strong>ID:</strong> ${departamento.idDepartamento}
                            </span>

                            <div class="acoes">
                                <button class="btn-editar" title="Editar"
                                    onclick="editarDepartamento(${departamento.idDepartamento})">✏️</button>

                                <button class="btn-excluir" title="Excluir"
                                    onclick="confirmarExclusao(${departamento.idDepartamento})">❌</button>
                            </div>
                        `;

                        containerDepartamentos.appendChild(div);
                    });

                    ocultarAcoesSeNaoGerente();

                } catch (error) {
                    containerDepartamentos.innerHTML =
                        `<p style="color: red;">Erro ao pesquisar departamentos: ${error.message}</p>`;
                }
            }
        });
    })
    .catch(error => {
        document.getElementById('header').innerHTML =
            `<p>Erro ao carregar o header: ${error.message}</p>`;
    });


function logout() {
    sessionStorage.removeItem('accessToken');
    window.location.replace('/pages/html/login.html');
}


// Funções reutilizadas do carrega-funcionarios.js
function editarFuncionario(id) {
    window.location.href = `edicao-funcionario.html?id=${id}`;
}

async function confirmarExclusao(id) {
    const confirmacao = confirm("Tem certeza que deseja excluir este funcionário?");
    if (!confirmacao) return;

    try {

        const accessToken = sessionStorage.getItem('accessToken');

        if (!accessToken) {
            window.location.replace('/pages/html/login.html');
            return;
        }

        const resposta = await fetch(`/funcionarios/${id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });

        if (resposta.status === 401) {
            sessionStorage.removeItem('accessToken');
            window.location.replace('/pages/html/login.html');
            return;
        }

        if (resposta.ok) {
            alert("Funcionário excluído com sucesso!");
            window.location.reload();
        } else {
            const erro = await resposta.text();
            alert("Erro ao excluir: " + erro);
        }
    } catch (erro) {
        alert("Erro de rede ao excluir: " + erro.message);
    }
}


