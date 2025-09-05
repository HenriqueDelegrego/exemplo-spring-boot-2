const form = document.getElementById("formEdicaoFuncionario");
const params = new URLSearchParams(window.location.search);
const funcionarioId = params.get("id");

async function carregarDepartamentosSelecionaveis(departamentoAtualId) {
    const select = document.getElementById("departamentosSelect");

    try {
        const res = await fetch("http://localhost:8080/departamentos");
        const departamentos = await res.json();

        departamentos.forEach(dep => {
            const option = document.createElement("option");
            option.value = dep.idDepartamento;
            option.textContent = dep.nmDepartamento;
            if (dep.idDepartamento === departamentoAtualId) {
                option.selected = true;
            }
            select.appendChild(option);
        });
    } catch (err) {
        alert("Erro ao carregar departamentos: " + err.message);
    }
}

async function carregarFuncionario() {
    try {
        const response = await fetch(`http://localhost:8080/funcionarios/${funcionarioId}`);
        if (!response.ok) throw new Error("Funcionário não encontrado");

        const func = await response.json();
        form.id.value = func.idFuncionario;
        form.criadoPor.value = func.criadoPor?.idFuncionario || "";
        form.nome.value = func.nome;
        form.cpf.value = func.cpf;
        form.email.value = func.email;
        form.senha.value = func.senha;
        form.dataNascimento.value = func.dataNascimento;
        form.salario.value = func.salario;
        form.gerente.checked = func.gerente;

        form.estado.value = func.endereco?.estado || "";
        form.cidade.value = func.endereco?.cidade || "";
        form.bairro.value = func.endereco?.bairro || "";
        form.logradouro.value = func.endereco?.logradouro || "";
        form.numero.value = func.endereco?.numero || "";
        form.cep.value = func.endereco?.cep || "";

        await carregarDepartamentosSelecionaveis(func.departamento?.idDepartamento);

    } catch (error) {
        alert("Erro ao carregar funcionário: " + error.message);
    }
}

form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const criadoPorId = parseInt(form.criadoPor.value);
    const funcionario = {
        idFuncionario: parseInt(form.id.value),
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
            cep: form.cep.value,
        },
        departamento: {
            idDepartamento: parseInt(form.departamento.value),
        },
        criadoPor: criadoPorId ? {
            idFuncionario: criadoPorId
        } : null // Envia null se o valor for inválido
    };

    try {
        const res = await fetch("http://localhost:8080/funcionarios", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(funcionario),
        });

        if (res.ok) {
            alert("Funcionário atualizado com sucesso!");
            window.location.href = "lista-funcionarios.html";
        } else {
            const erro = await res.text();
            alert("Erro ao atualizar: " + erro);
        }
    } catch (erro) {
        alert("Erro de rede: " + erro.message);
    }
});

carregarFuncionario();