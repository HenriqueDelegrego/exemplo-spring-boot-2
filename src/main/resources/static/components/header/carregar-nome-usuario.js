async function carregarNomeUsuarios() {
  fetch('/funcionarios/validacao')
    .then(response => {
      return response.json();
    })
    .then(funcionario => {
      document.getElementById('nome-usuario').innerHTML = funcionario.nome;
    })
    .catch(error => {
      console.error(error);
    });

}

carregarNomeUsuarios();
