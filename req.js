fetch('/message/').then(response => response.json().then(console.log))

fetch('/message/2').then(response => response.json().then(console.log))

fetch(
  '/message',
  {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ text: 'Fourth message (4)', id: 10 })
  }
).then(result => result.json().then(console.log))

fetch(
  '/message/4',
  {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ text: 'Fourth message', id: 10 })
  }
).then(result => result.json().then(console.log));

fetch('/message/4', { method: 'DELETE' }).then(result => console.log(result))