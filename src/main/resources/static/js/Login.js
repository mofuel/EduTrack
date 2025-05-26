  function openModal() {
    document.getElementById('modalRecuperar').style.display = 'block';
  }

  function closeModal() {
    document.getElementById('modalRecuperar').style.display = 'none';
  }

  // Cierra el modal al hacer clic fuera
  window.onclick = function(event) {
    const modal = document.getElementById('modalRecuperar');
    if (event.target == modal) {
      closeModal();
    }
  }

  function openTokenModal() {
      document.getElementById('modalToken').style.display = 'block';
  }

  function closeTokenModal() {
      document.getElementById('modalToken').style.display = 'none';
      clearTokenMessages();
  }

  // Limpia mensajes en modal recuperar
  function clearRecoverMessages() {
      document.getElementById('emailError').style.display = 'none';
      document.getElementById('emailSuccess').style.display = 'none';
  }

  // Limpia mensajes en modal token
  function clearTokenMessages() {
      document.getElementById('tokenError').style.display = 'none';
  }
let cooldown = false;

document.getElementById('recuperarForm').addEventListener('submit', function(e) {
    e.preventDefault();
    if(cooldown) return; // evitar envíos mientras está en cooldown
    clearRecoverMessages();

    const email = document.getElementById('recoverEmail').value;

    fetch('/auth/recuperar', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: new URLSearchParams({email})
    })
    .then(res => res.json())
    .then(data => {
        if(data.error){
            const emailError = document.getElementById('emailError');
            emailError.textContent = data.error;
            emailError.style.display = 'block';
        } else {
            const emailSuccess = document.getElementById('emailSuccess');
            emailSuccess.textContent = data.mensaje;
            emailSuccess.style.display = 'block';

            // Guardar letra en input del modal token
            document.getElementById('tokenLetra').value = data.letraToken || '';

            // Abrir modal token y cerrar modal recuperar
            setTimeout(() => {
                closeModal();
                openTokenModal();
            }, 1500);

            // Iniciar cooldown 30 segundos
            iniciarCooldown();
        }
    })
    .catch(err => {
        console.error('Error en recuperar:', err);
    });
});

function iniciarCooldown(){
    cooldown = true;
    document.getElementById('enviarTokenBtn').disabled = true;
    document.getElementById('cooldownMsg').style.display = 'block';

    let seg = 30;
    const intervalo = setInterval(() => {
        seg--;
        document.getElementById('cooldownMsg').textContent = `Espera ${seg} segundos para enviar otro token.`;
        if(seg <= 0){
            clearInterval(intervalo);
            cooldown = false;
            document.getElementById('enviarTokenBtn').disabled = false;
            document.getElementById('cooldownMsg').style.display = 'none';
        }
    }, 1000);
}

document.getElementById('tokenForm').addEventListener('submit', function(e) {
    e.preventDefault();
    clearTokenMessages();

    const letra = document.getElementById('tokenLetra').value;
    const numeros = document.getElementById('tokenNumeros').value;

    // Validar que números son 4 dígitos numéricos
    if(!/^\d{4}$/.test(numeros)){
        const tokenError = document.getElementById('tokenError');
        tokenError.textContent = "Debes ingresar 4 dígitos numéricos";
        tokenError.style.display = 'block';
        return;
    }

    const tokenCompleto = letra + '-' + numeros;

    fetch(`/auth/verificar?token=${encodeURIComponent(tokenCompleto)}`)
    .then(res => {
        if(res.ok){
            return res.json();
        } else {
            return res.json().then(err => Promise.reject(err));
        }
    })
    .then(data => {
        window.location.href = `/cambiarcontraseña?token=${encodeURIComponent(tokenCompleto)}`;
    })
    .catch(err => {
        const tokenError = document.getElementById('tokenError');
        tokenError.textContent = err.error || 'Token inválido o expirado';
        tokenError.style.display = 'block';
    });
});