import { useRef } from "react";
import { ImportIcon } from "../icons";

function Import() {
  const fileInputRef = useRef(null);

  async function onChange(event: React.ChangeEvent<HTMLInputElement>) {
    if (event.target.files && event.target.files.length > 0) {
      const formData = new FormData();
      formData.append("file", event.target.files[0]);

      await fetch("http://localhost:8080/transaction/import", {
        body: formData,
        method: "POST"
      })
        .then((res) => res.json)
        .then((data) => {
          console.log("Import transaction response: ", data);
        })
        .catch((err) => {
          console.log("Error calling import transaction: ", err);
        });
    }
  };

  function onClick() {
    if (fileInputRef.current !== null) {
      (fileInputRef.current as HTMLInputElement).click();
    }
  }

  return <div>
    <input accept="text/csv" hidden onChange={onChange} ref={fileInputRef} type="file" />
    <button className="bg-gray hover:bg-purple p-2 rounded-full cursor-pointer" onClick={onClick}>
      {ImportIcon()}
    </button>
  </div>;
}

export default Import;

