import { useState } from "react";
import AddTransactionForm from "./components/AddTransactionForm";
import ViewTransactions from "./components/ViewTransactions";
import { AddIcon } from "./icons";
import Import from "./components/Import";
import Export from "./components/Export";
import { AnimatePresence, motion } from "motion/react";

function Homepage() {
  const [isViewForm, setIsViewForm] = useState<boolean>(false);
  const [isMobileScreen, setIsMobileScreen] = useState<boolean>(checkScreen());
  const motionHide = isMobileScreen ?
    { height: 0, opacity: 0 } : { width: 0, opacity: 0 };
  const motionShow = isMobileScreen ?
    { height: "auto", opacity: 1 } : { width: "auto", opacity: 1 };

  function onClick() {
    setIsViewForm(!isViewForm);
  }

  function checkScreen(): boolean {
    return window.innerWidth < 768;
  }

  window.addEventListener("resize", () => {
    setIsMobileScreen(checkScreen());
    console.log("isMobileScreen: ", isMobileScreen);
  });

  return <div className="flex flex-col md:flex-row h-screen items-center p-4 pr-16 w-screen overflow-hidden">
    <div className="fixed right-4 top-4 flex gap-4 flex flex-col">
      <Import />
      <Export />
    </div>
    <ViewTransactions />
    <AnimatePresence>
      {
        isViewForm &&
        <motion.div
          initial={motionHide}
          animate={motionShow}
          exit={motionHide}
        >
          <AddTransactionForm />
        </motion.div>
      }
    </AnimatePresence>
    <button className="bg-gray hover:bg-purple bottom-4 fixed p-2 right-4 rounded-full cursor-pointer" onClick={onClick} title="New">
      {AddIcon()}
    </button>
  </div>;
}

export default Homepage;

