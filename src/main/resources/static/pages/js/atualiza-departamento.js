// Salva alterações
document.getElementById('form-editar').onsubmit = function (e) {
    e.preventDefault();
    fetch('/departamentos', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            idDepartamento: document.getElementById('idDepartamento').value,
            nmDepartamento: document.getElementById('nome').value
        })
    }).then(resp => {
        if (resp.ok) {
            alert('Departamento atualizado!');
            window.location.href = '../html/lista-departamentos.html';
        } else {
            alert('Erro ao atualizar departamento.');
        }
    });
};