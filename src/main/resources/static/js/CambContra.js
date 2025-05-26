// Obtener token del query param y setear input oculto
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    if(!token){
        document.getElementById('mensaje').textContent = 'Token no proporcionado';
    } else {
        document.getElementById('tokenInput').value = token;
    }

    document.getElementById('cambiarForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const nuevaPassword = document.getElementById('nuevaPassword').value;

        fetch('/auth/cambiar-password', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: new URLSearchParams({token, nuevaPassword})
        })
        .then(res => res.json())
        .then(data => {
            if(data.error){
                document.getElementById('mensaje').textContent = data.error;
            } else {
                alert(data.mensaje);
                window.location.href = '/login';
            }
        })
        .catch(err => {
            document.getElementById('mensaje').textContent = 'Error en la solicitud';
        });
    });

function mostrarRequisitos() {
    const requisitosDiv = document.getElementById('passwordRequisitos');
    if (requisitosDiv) {
        requisitosDiv.style.display = 'block';
    }
}

function validarPassword() {
    const pwdInput = document.getElementById('passwordRegistro');
    const pwd = pwdInput ? pwdInput.value : '';

    const longitudValida = pwd.length >= 6 && pwd.length <= 12;
    const mayusculaValida = /[A-Z]/.test(pwd);
    const especialValido = /[!@#$%^&*(),.?":{}|<>]/.test(pwd);

    const longitudP = document.getElementById('longitud');
    const mayusculaP = document.getElementById('mayuscula');
    const especialP = document.getElementById('especial');

    if (longitudP) {
        longitudP.style.color = longitudValida ? 'green' : 'red';
        longitudP.innerHTML = (longitudValida ? '&#9989;' : '&#10060;') + ' La contraseña debe tener entre 6 y 12 caracteres';
    }

    if (mayusculaP) {
        mayusculaP.style.color = mayusculaValida ? 'green' : 'red';
        mayusculaP.innerHTML = (mayusculaValida ? '&#9989;' : '&#10060;') + ' La contraseña debe tener al menos una mayúscula';
    }

    if (especialP) {
        especialP.style.color = especialValido ? 'green' : 'red';
        especialP.innerHTML = (especialValido ? '&#9989;' : '&#10060;') + ' La contraseña debe tener al menos un carácter especial';
    }
}
