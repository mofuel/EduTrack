// Obtener la lista de usuarios desde la API y mostrarla en la tabla
function cargarUsuarios() {
    fetch('/api/usuarios')
        .then(response => response.json())
        .then(usuarios => {
            let tbody = document.getElementById('usuariosTableBody');
            tbody.innerHTML = ''; // Limpiar tabla antes de agregar los nuevos usuarios
            usuarios.forEach(usuario => {
                let tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${usuario.id}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.apellido}</td>
                    <td>${usuario.dni}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.telefono}</td>
                    <td>${usuario.rol}</td>
                    <td>
                        <button class="btn btn-warning btn-sm"
                                onclick="abrirModalEditar('${usuario.id}', '${usuario.nombre}', '${usuario.apellido}', '${usuario.dni}', '${usuario.email}', '${usuario.telefono}', '${usuario.rol}')">
                            Editar
                        </button>
                        <button class="btn btn-danger btn-sm"
                                onclick="eliminarUsuario('${usuario.id}')">
                            Eliminar
                        </button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error al cargar los usuarios:', error);
        });
}

// Función para abrir el modal de edición
function abrirModalEditar(id, nombre, apellido, dni, email, telefono, rol) {
    document.getElementById('editar-id').value = id;
    document.getElementById('editar-nombre').value = nombre;
    document.getElementById('editar-apellido').value = apellido;
    document.getElementById('editar-dni').value = dni;
    document.getElementById('editar-email').value = email;
    document.getElementById('editar-telefono').value = telefono;
    document.getElementById('editar-rol').value = rol;

    let modal = new bootstrap.Modal(document.getElementById('modalEditar'));
    modal.show();
}

// Función para eliminar un usuario
function eliminarUsuario(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
        fetch(`/api/usuarios/eliminar/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar');
            }
            cargarUsuarios(); // Actualiza la lista de usuarios después de eliminar
        })
        .catch(error => {
            console.error('Error al eliminar:', error);
            alert('Hubo un error al eliminar el usuario.');
        });
    }
}

// Función para manejar la edición de usuario
document.addEventListener('DOMContentLoaded', function () {
    cargarUsuarios(); // Cargar los usuarios al cargar la página

    document.getElementById('formEditarUsuario').addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('editar-id').value;
        const nombre = document.getElementById('editar-nombre').value;
        const apellido = document.getElementById('editar-apellido').value;
        const dni = document.getElementById('editar-dni').value;
        const email = document.getElementById('editar-email').value;
        const telefono = document.getElementById('editar-telefono').value;
        const rol = document.getElementById('editar-rol').value;

        const datosActualizados = {
            id: id,
            nombre: nombre,
            apellido: apellido,
            dni: dni,
            email: email,
            telefono: telefono,
            rol: rol,
            contraseña: "" // Si no se cambia la contraseña, se puede dejar como string vacío o null
        };

        fetch(`/api/usuarios/actualizar/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosActualizados)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar');
            }
            return response.json();
        })
        .then(usuarioActualizado => {
            bootstrap.Modal.getInstance(document.getElementById('modalEditar')).hide();
            cargarUsuarios(); // Actualiza la lista de usuarios después de la edición
        })
        .catch(error => {
            console.error('Error al actualizar:', error);
            alert('Hubo un error al actualizar el usuario.');
        });
    });
});
