fetch('../../components/header/header.html')
    .then(response => response.text())
    .then(header => {
        document.getElementById('header').innerHTML = header;
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '../../components/header/header-style.css';
        document.head.appendChild(link);

        // Adiciona o evento ao input de pesquisa após o header ser carregado
        const inputPesquisa = document.getElementById('input-pesquisa');
        if (inputPesquisa) {
            inputPesquisa.addEventListener('input', function () {
                const pesquisa = inputPesquisa.value;

                if (pesquisa === '') {
                    document.getElementById('funcionarios').innerHTML = '';
                    return;
                }

                fetch(`/funcionarios/search?pesquisa=${pesquisa}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Erro ao buscar funcionários.');
                        }
                        return response.json();
                    })
                    .then(funcionarios => {
                        const container = document.getElementById('funcionarios');
                        container.innerHTML = '';

                        if (funcionarios.length === 0) {
                            container.innerHTML = '<p>Nenhum funcionário encontrado.</p>';
                            return;
                        }

                        funcionarios.forEach(func => {
                            const div = document.createElement('div');
                            div.className = 'funcionario';

                            div.innerHTML = `
                                <div class="acoes">
                                    <button class="btn-editar" title="Editar" onclick="editarFuncionario(${func.idFuncionario})">✏️</button>
                                    <button class="btn-excluir" title="Excluir" onclick="confirmarExclusao(${func.idFuncionario})">❌</button>
                                </div>
                                <strong>Nome:</strong> <span>${func.nome}</span>
                                <strong>CPF:</strong> <span>${func.cpf}</span>
                                <strong>Email:</strong> <span>${func.email}</span>
                                <strong>Data de Nascimento:</strong> <span>${new Date(func.dataNascimento).toLocaleDateString('pt-BR')}</span>
                                <strong>Salário:</strong> <span>R$ ${func.salario?.toFixed(2) || '0.00'}</span>
                                <strong>Função:</strong> <span>${func.gerente ? 'Gerente' : 'Funcionário'}</span>
                                <strong>Departamento:</strong> <span>${func.departamento?.nmDepartamento || 'N/A'}</span>
                                <strong>Criado por:</strong> <span>${func.criadoPor || 'N/A'}</span>
                                <strong>Endereço:</strong>
                                <span>${func.endereco?.logradouro || ''}, ${func.endereco?.numero || ''} - ${func.endereco?.bairro || ''}</span>
                                <span>${func.endereco?.cidade || ''} - ${func.endereco?.estado || ''}</span>
                                <span>CEP: ${func.endereco?.cep || ''}</span>
                            `;

                            container.appendChild(div);
                        });
                    })
                    .catch(error => {
                        console.error('Erro:', error);
                        document.getElementById('funcionarios').innerHTML =
                            `<p style="color: red;">Erro ao carregar funcionários: ${error.message}</p>`;
                    });
            });
        }
    })
    .catch(error => {
        document.getElementById('header').innerHTML = `<p>Erro ao carregar o header</p>`;
    });

// Funções reutilizadas do carrega-funcionarios.js
function editarFuncionario(id) {
    window.location.href = `edicao-funcionario.html?id=${id}`;
}

async function confirmarExclusao(id) {
    const confirmacao = confirm('Tem certeza que deseja excluir este funcionário?');
    if (!confirmacao) return;

    try {
        const resposta = await fetch(`http://localhost:8080/funcionarios/${id}`, {
            method: 'DELETE',
        });

        if (resposta.ok) {
            alert('Funcionário excluído com sucesso!');
            window.location.reload();
        } else {
            const erro = await resposta.text();
            alert('Erro ao excluir: ' + erro);
        }
    } catch (erro) {
        alert('Erro de rede ao excluir: ' + erro.message);
    }
}


