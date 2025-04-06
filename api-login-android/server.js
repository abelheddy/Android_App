const { Pool } = require('pg');

// Configuración para Render (usa variables de entorno)
const pool = new Pool({
  user: process.env.DB_USER,
  host: process.env.DB_HOST,
  database: process.env.DB_NAME,
  password: process.env.DB_PASSWORD,
  port: process.env.DB_PORT || 5432, // Puerto default de PostgreSQL
  ssl: process.env.DB_SSL ? { rejectUnauthorized: false } : false // Necesario para Render
});

// Ejemplo de query en el login (ahora con PostgreSQL)
app.post('/login', async (req, res) => {
  const { email, password } = req.body;
  try {
    const { rows } = await pool.query('SELECT * FROM users WHERE email = $1', [email]);
    const user = rows[0];
    // ... resto del código (bcrypt.compare, JWT, etc.)
  } catch (error) {
    res.status(500).json({ error: 'Error en el servidor' });
  }
});