// async function obterUsuarioAutenticado() {
//     try {
//         const response = await fetch('/funcionarios/me/id');
//         if (!response.ok) throw new Error('Erro ao obter usuário autenticado');
//         return await response.json();
//     } catch (error) {
//         console.error('Erro ao obter usuário autenticado:', error);
//         alert('Não foi possível obter o usuário autenticado. Tente novamente.');
//         throw error;
//     }
// }

async function cadastrarFuncionario() {
    document.getElementById('formFuncionario').addEventListener('submit', async function (e) {
        e.preventDefault();

        const form = e.target;

        try {
            // Obter o usuário autenticado
            // const usuarioAutenticado = await obterUsuarioAutenticado();

            const funcionario = {
                nome: form.nome.value,
                cpf: form.cpf.value,
                email: form.email.value,
                senha: form.senha.value,
                dataNascimento: form.dataNascimento.value,
                salario: parseFloat(form.salario.value) || 0,
                gerente: form.gerente.checked,
                endereco: {
                    estado: form.estado.value,
                    cidade: form.cidade.value,
                    bairro: form.bairro.value,
                    logradouro: form.logradouro.value,
                    numero: form.numero.value,
                    cep: form.cep.value
                },
                departamento: {
                    idDepartamento: parseInt(form.departamento.value)
                }           //  ,
                // criadoPor: {
                //     idFuncionario: usuarioAutenticado.id // Preenche com o ID do usuário autenticado
                // }
            };

            const response = await fetch('http://localhost:8080/funcionarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(funcionario)
            });

            if (response.ok) {
                alert('Funcionário cadastrado com sucesso!');
                form.reset();
            } else {
                const errorText = await response.text();
                alert('Erro ao cadastrar: ' + errorText);
            }
        } catch (error) {
            alert('Erro ao cadastrar funcionário: ' + error.message);
        }
    });
}

cadastrarFuncionario();