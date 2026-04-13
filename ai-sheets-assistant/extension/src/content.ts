console.log("AI Assistant injected into Google Sheets")
chrome.runtime.onMessage.addListener((msg, sender, sendResponse) => {

  if (msg.type === "GET_SELECTION") {

    // TEMP MVP METHOD
    // Google Sheets stores copied data in clipboard format

    const fakeSelection = {
      headers: ["Name", "Revenue"],
      rows: [
        ["A", 100],
        ["B", 200]
      ]
    };

    sendResponse(fakeSelection);
  }

  return true;
});
