async function carregarNomeUsuario() {

  const accessToken = sessionStorage.getItem('accessToken');

  if (!accessToken) {
    redirecionarParaLogin();
    return;
  }
  try {
    const response = await fetch('/funcionarios/me', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${accessToken}`
      }
    });

    if (response.status === 401) {
      redirecionarParaLogin();
      return;
    }

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`);
    }

    const funcionario = await response.json();

    const elementoNome = document.getElementById('nome-usuario');

    if (!elementoNome) {
      console.warn('Elemento #nome-usuario não encontrado.');
      return;
    }

    elementoNome.textContent = funcionario.nome;

  } catch (error) {
    console.error('Erro ao carregar nome do usuário:', error);
  }
}

function redirecionarParaLogin() {
  sessionStorage.removeItem('accessToken');
  window.location.replace('/pages/html/login.html');
}

carregarNomeUsuario();
