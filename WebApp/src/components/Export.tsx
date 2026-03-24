import { useRef } from "react";
import { ExportIcon } from "../icons";

function Export() {
  const fileDownloadRef = useRef(null);

  async function onClick() {
    await fetch("http://localhost:8080/transaction/export")
      .then((res) => res.blob())
      .then((data) => {
        if (fileDownloadRef.current != null) {
          (fileDownloadRef.current as HTMLInputElement).setAttribute("href", window.URL.createObjectURL(data));
          (fileDownloadRef.current as HTMLInputElement).click();
          (fileDownloadRef.current as HTMLInputElement).remove();
        }
      })
      .catch((err) => {
        console.log("Exception while exporting: ", err);
      });
  }

  return <div>
    <a download="transactions.csv" ref={fileDownloadRef} />
    <button className="bg-gray hover:bg-purple p-2 rounded-full cursor-pointer" onClick={onClick} title="Export">
      {ExportIcon()}
    </button>
  </div>;
}

export default Export;
