document.getElementById('registroForm').addEventListener('submit', function(e) {
     e.preventDefault(); // evitar que se recargue la página

     const formData = new FormData(this);

     const data = {
         nombre: formData.get("nombre"),
         apellido: formData.get("apellido"),
         dni: formData.get("dni"),
         email: formData.get("email"),
         telefono: formData.get("telefono"),
         rol: formData.get("rol"),
         password: formData.get("password"),
         confirmPassword: formData.get("confirmPassword")
     };

     fetch('/api/usuarios/registro', {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json'
         },
         body: JSON.stringify(data)
     })
     .then(response => {
         if (!response.ok) {
             return response.text().then(text => { throw new Error(text); });
         }
         return response.text();
     })
     .then(message => {
         alert(message); // o redirige a login si quieres
         window.location.href = '/login';
     })
     .catch(error => {
         console.error('Error:', error.message);
         alert('Error al registrar: ' + error.message);
     });
 });

async function validarEmail() {
   const emailInput = document.getElementById("email");
   const emailErrorDiv = document.getElementById("emailError");
   const email = emailInput.value;

   if (!email) {
     emailErrorDiv.textContent = "";
     return;
   }

   try {
       const response = await fetch(`/api/usuarios/validar-email?email=${encodeURIComponent(email)}`);

       if (!response.ok) {
           throw new Error("Error en la respuesta de validación");
       }

       const data = await response.json();
       const existe = data.existe;

       if (existe) {
           emailErrorDiv.textContent = "Este correo ya está registrado.";
       } else {
           emailErrorDiv.textContent = "";
       }
   } catch (error) {
       emailErrorDiv.textContent = "Error validando email.";
   }
}

async function validarDni() {
  const dniInput = document.getElementById("dni");
  const dniErrorDiv = document.getElementById("dniError");
  const dni = dniInput.value.trim();

  if (!dni) {
    dniErrorDiv.textContent = "";
    return;
  }

  try {
    const response = await fetch(`/api/usuarios/validar-dni?dni=${encodeURIComponent(dni)}`);
    if (!response.ok) {
      throw new Error("Error en la respuesta de validación del DNI");
    }

    const data = await response.json();

    if (data.existe) {
      dniErrorDiv.textContent = "Este DNI ya está registrado.";
    } else {
      dniErrorDiv.textContent = "";
    }
  } catch (error) {
    dniErrorDiv.textContent = "Error validando DNI.";
  }
}

async function validarTelefono() {
  const telefonoInput = document.getElementById("telefono");
  const telefonoErrorDiv = document.getElementById("telefonoError");
  const telefono = telefonoInput.value.trim();

  if (!telefono) {
    telefonoErrorDiv.textContent = "";
    return;
  }

  try {
    const response = await fetch(`/api/usuarios/validar-telefono?telefono=${encodeURIComponent(telefono)}`);

    if (!response.ok) {
      throw new Error("Error en la respuesta de validación del teléfono");
    }

    const data = await response.json();

    if (data.existe) {
      telefonoErrorDiv.textContent = "Este teléfono ya está registrado.";
    } else {
      telefonoErrorDiv.textContent = "";
    }
  } catch (error) {
    telefonoErrorDiv.textContent = "Error validando teléfono.";
  }
}

function mostrarRequisitos() {
  document.getElementById('passwordRequisitos').style.display = 'block';
}

function validarPassword() {
  const pwd = document.getElementById('passwordRegistro').value;

  const longitudValida = pwd.length >= 6 && pwd.length <= 12;
  const mayusculaValida = /[A-Z]/.test(pwd);
  const especialValido = /[!@#$%^&*(),.?":{}|<>]/.test(pwd);

  const longitudP = document.getElementById('longitud');
  const mayusculaP = document.getElementById('mayuscula');
  const especialP = document.getElementById('especial');

  longitudP.style.color = longitudValida ? 'green' : 'red';
  longitudP.innerHTML = (longitudValida ? '&#9989;' : '&#10060;') + ' La contraseña debe tener entre 6 y 12 caracteres';

  mayusculaP.style.color = mayusculaValida ? 'green' : 'red';
  mayusculaP.innerHTML = (mayusculaValida ? '&#9989;' : '&#10060;') + ' La contraseña debe tener al menos una mayúscula';

  especialP.style.color = especialValido ? 'green' : 'red';
  especialP.innerHTML = (especialValido ? '&#9989;' : '&#10060;') + ' La contraseña debe tener al menos un carácter especial';
}