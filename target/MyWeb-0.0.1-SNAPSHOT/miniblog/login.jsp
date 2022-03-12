<!DOCTYPE html>
<html>
    <body>
        <h1>Bem-vindo!</h1>
        <form action="ControleBlog">
            <span style="color: red">${ERRO_BEAN}</span>
             <span style="color: red">${usuario}</span>
            <div>
            <label>Informe seu nome:
                <input type="text" name="nome">
            </label>
            <button type="submit">Entrar</button>
            </div>
        </form>
    </body>
</html>

