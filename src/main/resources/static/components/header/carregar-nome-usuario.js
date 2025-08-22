fetch('/funcionarios/validacao')
  .then(response => {
    return response.json();
  })
  .then(funcionario => {
    console.log(funcionario);
    document.getElementById('nome-usuario').innerText = funcionario.nome;
  })
  .catch(err => {
    console.error(err);
    document.getElementById('nome-usuario').innerText = "Guest";
  });
