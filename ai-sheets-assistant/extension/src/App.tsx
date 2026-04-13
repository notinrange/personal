import { useState } from 'react'
import './App.css'

function App() {
  const [prompt, setPrompt] = useState("");
  const [output, setOutput] = useState("");

  const handleGenerate = async () => {

    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });

    const selection = await chrome.tabs.sendMessage(tab.id!, {
      type: "GET_SELECTION"
    });

    const res = await fetch("http://localhost:8080/api/v1/generate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        prompt,
        selection
      })
    });

    const data = await res.json();

    setOutput(data.formula);
  };


  return (
    <div style={{ width: 350, padding: 16 }}>
      <h2>AI Sheets Assistant</h2>

      <textarea
        placeholder="Ask AI to generate a formula..."
        value={prompt}
        onChange={(e) => setPrompt(e.target.value)}
        style={{ width: "100%", height: 80 }}
      />

      <button onClick={handleGenerate}>
        Generate
      </button>

      <pre>{output}</pre>
    </div>
  )
}

export default App
